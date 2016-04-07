package com.jjortega.cuapi.requestcore;

import java.util.List;

import org.apache.http.Header;

import com.jjortega.cuapi.annotations.Method;
import com.jjortega.cuapi.entity.Entity;

public abstract class CommonRequest {

	private HttpClientWrapper httpClient;

	private HttpRequestWrapper httpRequest;

	private ResponseMap expectedResponseMap;
	private HttpResponseWrapper httpResponse;

	public CommonRequest() {
		this.httpClient = HttpClientWrapper.getInstance();
		this.httpRequest = new HttpRequestWrapper(getClass());
		this.httpRequest.setMethod(getClass().getAnnotation(Method.class)
				.value());
		this.expectedResponseMap = new ResponseMap();
	}

	protected void addResponseMap(int httpStatusCode,
			Class<? extends Entity> responseModel) {
		expectedResponseMap.addResponse(httpStatusCode, responseModel);
	}

	public void setResource(String resource) {
		this.httpRequest.setResource(resource);
	}

	public void run() {
		this.httpResponse = httpClient.run(httpRequest, expectedResponseMap);
	}

	public CommonRequest addHeader(String header, String value) {
		httpRequest.addHeader(header, value);
		return this;
	}

	public int getStatusCode() {
		return httpResponse.getHttpStatusCode();
	}

	public List<Header> getRequestHeaders() {
		return httpRequest.getRequestHeaders();
	}

	public List<Header> getResponseHeaders() {
		return httpResponse.getAllHeaders();
	}

	public String getBaseUrl() {
		return this.httpRequest.getBaseURL();
	}

	public String getCompleteURI() {
		return httpRequest.getURI();
	}

	public String getResponseAsJson() {
		return httpResponse.getResponseAsJson();
	}

	public Entity getResponseAsEntity() {
		return httpResponse.getResponseAsEntity();
	}

	public void setRequestObject(Entity requestObject) {
		httpRequest.setRequestObject(requestObject);
	}

	public void setRequestJson(String json) {
		httpRequest.setRequestJson(json);
	}

	public String getRequestAsJson() {
		return httpRequest.getRequestJson();
	}
}
