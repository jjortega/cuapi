package com.jjortega.cuapi.requestcore;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpClientWrapper {

	private static HttpClientWrapper instance = null;

	private HttpClient httpClient;

	private HttpClientWrapper() {
		this.httpClient = HttpClientBuilder.create().build();
	}

	public HttpResponseWrapper run(HttpRequestWrapper httpRequest,
			ResponseMap expectedResponseMap) {
		try {
			return new HttpResponseWrapper(httpClient.execute(httpRequest
					.getRequestReadyToRun()), expectedResponseMap);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static HttpClientWrapper getInstance() {
		if (instance == null) {
			instance = new HttpClientWrapper();
		}
		return instance;
	}

}
