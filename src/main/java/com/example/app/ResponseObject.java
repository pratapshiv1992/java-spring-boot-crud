package com.example.app;

class ResponseObject {
	private Integer statusCode;
	private String xml;
	private Boolean error;
	public ResponseObject(Integer statusCode, String xml, Boolean error) {
		super();
		this.statusCode = statusCode;
		this.xml = xml;
		this.error = error;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public String getXml() {
		return xml;
	}
	public Boolean getError() {
		return error;
	}
}