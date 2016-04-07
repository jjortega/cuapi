package com.jjortega.cuapi.requestcore;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;

import com.jjortega.cuapi.annotations.Target;
import com.jjortega.cuapi.entity.Entity;

public class HttpRequestWrapper {

	private String baseUrl;
	private String resource = "/";

	protected HttpRequestBase httpRequest;
	private String httpRestMethod;

	protected List<Header> requestHeaders = new ArrayList<Header>();

	private String requestJson = "";

	public HttpRequestWrapper(Class<? extends CommonRequest> clazz) {
		setBaseUrl(clazz);
	}

	private void setBaseUrl(Class<? extends CommonRequest> clazz) {
		this.baseUrl = GetProperties.getPropValue(clazz.getAnnotation(
				Target.class).value());
	}

	public void setMethod(String httpRestMethod) {
		switch (httpRestMethod) {
		case "GET":
			httpRequest = new HttpGet();
			break;
		case "POST":
			httpRequest = new HttpPost();
			break;
		case "PUT":
			httpRequest = new HttpPut();
			break;
		case "DELETE":
			httpRequest = new HttpDelete();
			break;
		case "HEAD":
			httpRequest = new HttpHead();
			break;
		case "OPTIONS":
			httpRequest = new HttpOptions();
			break;
		default:
			throw new NotValidHttpRestMethodExpection("The method "
					+ httpRestMethod + " is not valid");
		}
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public void addHeader(String header, String value) {
		for (int i = 0; i < requestHeaders.size(); i++) {
			if (requestHeaders.get(i).getName().equalsIgnoreCase(header)) {
				requestHeaders.set(i, new BasicHeader(header, value));
				return;
			}
		}
		requestHeaders.add(new BasicHeader(header, value));
	}

	public void setRequestObject(Entity requestObject) {
		setRequestJson(requestObject.toJson());
	}

	public void setRequestJson(String json) {
		this.requestJson = json;
	}

	public HttpUriRequest getRequestReadyToRun() {
		prepareRequest();
		return httpRequest;
	}

	private void prepareRequest() {
		this.setURIToRequest().addHeadersToRequest().setRequestJsonToRequest();
	}

	private HttpRequestWrapper setURIToRequest() {
		try {
			httpRequest.setURI(new URI(this.baseUrl + this.resource));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return this;
	}

	private HttpRequestWrapper addHeadersToRequest() {
		if (requestHeaders != null) {
			for (Header header : requestHeaders) {
				httpRequest.addHeader(header);
			}
		}
		return this;
	}

	private HttpRequestWrapper setRequestJsonToRequest() {
		if (httpRequest instanceof HttpEntityEnclosingRequest) {
			try {
				((HttpEntityEnclosingRequest) httpRequest)
						.setEntity(new StringEntity(requestJson));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClassCastException e) {
				throw new NotAllowedRequestBodyException("The method "
						+ httpRestMethod + " does not allow request body");
			}
		}
		return this;
	}

	public String getBaseURL() {
		return this.baseUrl;
	}

	public String getURI() {
		return this.baseUrl + this.resource;
	}

	public List<Header> getRequestHeaders() {
		return requestHeaders;
	}

	public String getRequestJson() {
		return requestJson;
	}

}
