package com.jjortega.cuapi.testwrapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import com.jjortega.cuapi.entity.Entity;

public class RequestTestResultsModel extends Entity {
	
	private String URL;

	private String httpStatusCode;
	private List<BasicHeader> requestHeaders;
	private List<BasicHeader> responseHeaders;
	private String requestBody;
	private String responseBody;

	public RequestTestResultsModel() {
		super();
	}
	
	public String getURL() {
		return URL;
	}

	public void setURL(String URL) {
		this.URL = URL;
	}

	public String getHttpStatusCode() {
		return httpStatusCode;
	}

	public List<BasicHeader> getRequestHeaders() {
		return requestHeaders;
	}

	public List<BasicHeader> getResponseHeaders() {
		return responseHeaders;
	}

	public void setRequestHeaders(List<Header> requestHeaders) {
		List<BasicHeader> listBasicHeaders = new ArrayList<BasicHeader>();
		for (Header h : requestHeaders) {
			BasicHeader basicHeader = new BasicHeader(h.getName(), h.getValue());
			listBasicHeaders.add(basicHeader);
		}	
		this.requestHeaders = listBasicHeaders;
	}

	public void setResponseHeaders(List<Header> responseHeaders) {
		List<BasicHeader> listBasicHeaders = new ArrayList<BasicHeader>();
		for (Header h : responseHeaders) {
			BasicHeader basicHeader = new BasicHeader(h.getName(), h.getValue());
			listBasicHeaders.add(basicHeader);
		}	
		this.responseHeaders = listBasicHeaders;
	}

	public void setHttpStatusCode(String httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public String getRequestBody() {
		return requestBody;
	}

	public String getResponseBody() {
		return responseBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}
	
	@Override
	public String toString(){
		return toJson();
	}

}
