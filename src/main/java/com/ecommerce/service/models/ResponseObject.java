package com.ecommerce.service.models;

public class ResponseObject {
	private String statuString;
	private String messageString;
	private Object dataObject;

	public ResponseObject() {
	}

	public ResponseObject(String statuString, String messageString, Object dataObject) {
		super();
		this.statuString = statuString;
		this.messageString = messageString;
		this.dataObject = dataObject;
	}

	public String getStatuString() {
		return statuString;
	}

	public void setStatuString(String statuString) {
		this.statuString = statuString;
	}

	public String getMessageString() {
		return messageString;
	}

	public void setMessageString(String messageString) {
		this.messageString = messageString;
	}

	public Object getDataObject() {
		return dataObject;
	}

	public void setDataObject(Object dataObject) {
		this.dataObject = dataObject;
	}
}
