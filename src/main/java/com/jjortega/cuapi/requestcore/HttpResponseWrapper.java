package com.jjortega.cuapi.requestcore;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.jayway.jsonpath.internal.JsonFormatter;
import com.jjortega.cuapi.entity.Entity;

public class HttpResponseWrapper {

	private HttpResponse httpResponse;

	private ResponseMap expectedResponseMap;

	private int httpStatusCode;
	private List<Header> httpResponseHeaders;
	private String httpResponseBody;

	public HttpResponseWrapper(HttpResponse httpResponse,
			ResponseMap expectedResponseMap) {
		this.httpResponse = httpResponse;
		this.expectedResponseMap = expectedResponseMap;
		httpStatusCode = httpResponse.getStatusLine().getStatusCode();
		httpResponseHeaders = Arrays.asList(httpResponse.getAllHeaders());
		httpResponseBody = getBodyFromResponse();
	}

	private String getBodyFromResponse() {
		String bodyJson = null;
		try {
			bodyJson = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return JsonFormatter.prettyPrint(bodyJson);
	}

	public List<Header> getAllHeaders() {
		return httpResponseHeaders;
	}

	public String getHeaderValue(String headerName) {
		for (Header header : httpResponseHeaders) {
			if (header.getName().equals(headerName)) {
				return header.getValue();
			}
		}
		return null;
	}

	public String getResponseAsJson() {
		return httpResponseBody;
	}

	public Entity getResponseAsEntity() {
		Gson gson = new Gson();
		return gson.fromJson(httpResponseBody,
				expectedResponseMap.getResponse(httpStatusCode));
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

}
