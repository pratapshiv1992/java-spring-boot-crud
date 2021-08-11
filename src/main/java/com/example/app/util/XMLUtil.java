package com.example.app.util;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class XMLUtil {

    public static <T> String objectToXMLConverter(T obj) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter sw = new StringWriter();
        marshaller.marshal(obj, sw);
        return sw.toString();
    }

    public static <T> Object xmlToObjectConverter(String xmlDoc, T obj) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        return unmarshaller.unmarshal(new StringReader(xmlDoc));
    }

}
