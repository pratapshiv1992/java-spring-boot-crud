package com.example.app;

import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class DbConnection {
	private static final String ConnectionString = "jdbc:mysql://localhost:3306/xml_db?useSSL=false";
	private static final String Username = "root";
	private static final String Password = "";;
	private static final String INSERT_XML_OBJECT = "INSERT INTO json_xml_objects" + "  (type, object) VALUES "
			+ " (?, ?);";
	private static final String GET_XML_OBJECTS = "SELECT * FROM json_xml_objects;";
	private static final String GET_USER_BY_ID = "SELECT * FROM json_xml_objects where id=?;";
	private static final String UPDATE_USER_BY_ID = "UPDATE json_xml_objects SET object=? WHERE id=?";

	public static void insertRecord(String type, String object) throws SQLException {
		try (Connection connection = DriverManager.getConnection(ConnectionString, Username, Password);
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_XML_OBJECT);) {
			preparedStatement.setString(1, type);
			preparedStatement.setString(2, object);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public static List<User> getAllRecord() throws SQLException, JAXBException {
		List<User> userList = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection(ConnectionString, Username, Password);
				PreparedStatement preparedStatement = connection.prepareStatement(GET_XML_OBJECTS);) {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String object = rs.getString("object");
				User user = xmlToObjectCoverter(object);
				user.setId(id);
				userList.add(user);
			}

		} catch (SQLException e) {
			printSQLException(e);
		}
		return userList;
	}

	public static User xmlToObjectCoverter(String xmlStr) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		User user = (User) unmarshaller.unmarshal(new StringReader(xmlStr));
		return user;
	}

	public static <T> String objectToXMLCoverter(T obj) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		StringWriter sw = new StringWriter();
		marshaller.marshal(obj, sw);
		return sw.toString();
	}

	@SuppressWarnings("finally")
	public static Boolean updateUser(Integer id, User user) {
		Boolean isUserUpdate = false;
		try (Connection connection = DriverManager.getConnection(ConnectionString, Username, Password);
				PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				String xmlDoc = objectToXMLCoverter(user);
				PreparedStatement updatePreparedStatement = connection.prepareStatement(UPDATE_USER_BY_ID);
				updatePreparedStatement.setString(1, xmlDoc);
				updatePreparedStatement.setInt(2, id);
				Integer count = updatePreparedStatement.executeUpdate();
				isUserUpdate = count > 0;
			}

		} catch (SQLException e) {
			printSQLException(e);
		} catch (JAXBException e) {
				e.printStackTrace();
		} finally {
			return isUserUpdate;
		}

	}

	public static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}