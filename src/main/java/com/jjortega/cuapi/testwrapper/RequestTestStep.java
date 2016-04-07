package com.jjortega.cuapi.testwrapper;

import java.util.List;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

import org.apache.http.Header;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.jjortega.cuapi.container.CommonContainer;
import com.jjortega.cuapi.requestcore.CommonRequest;

import cucumber.api.Scenario;

public class RequestTestStep extends TestCase {

	private CommonRequest request;
	private Scenario scenario;

	private boolean hasBeenExecuted = false;

	private RequestTestResultsModel requestModel;

	private CommonContainer container;

	public RequestTestStep(Scenario scenario, CommonRequest request, CommonContainer container) {
		this.request = request;
		this.scenario = scenario;
		this.container = container;
		this.container.setRequestToList(this);
		this.requestModel = new RequestTestResultsModel();
	}

	public CommonRequest getRequest() {
		return request;
	}

	public void setRequest(CommonRequest request) {
		this.request = request;
	}

	public void execute() {
		request.run();
		this.hasBeenExecuted = true;
		writeResultData();
	}

	private void writeResultData() {
		requestModel
				.setHttpStatusCode(Integer.toString(request.getStatusCode()));
		requestModel.setRequestBody(request.getRequestAsJson());
		requestModel.setResponseBody(request.getResponseAsJson());
		requestModel.setRequestHeaders(request.getRequestHeaders());
		requestModel.setResponseHeaders(request.getResponseHeaders());
		scenario.write("<b>URL : </b>");
		scenario.write("<div style=\"text-indent: 5em;\">"
				+ request.getCompleteURI() + "</div>");
		scenario.write("<b>HTTPSTATUSCODE : </b>");
		scenario.write("<div style=\"text-indent: 5em;\">"
				+ Integer.toString(request.getStatusCode()) + "</div>");
		scenario.write("<b>REQUEST HEADERS : </b>");
		for (Header h : request.getRequestHeaders()) {
			scenario.write("<div style=\"text-indent: 5em;\">" + h.getName()
					+ " : " + h.getValue() + "</div>");
		}
		scenario.write("<b>REQUEST BODY : </b>");
		scenario.write("<pre style=\"margin-left: 5em;text-indent: 0em;\">"
				+ request.getRequestAsJson() + "</pre>");
		scenario.write("<b>RESPONSE HEADERS :</b>");
		for (Header h : request.getResponseHeaders()) {
			scenario.write("<div style=\"text-indent: 5em;\">" + h.getName()
					+ " : " + h.getValue() + "</div>");
		}
		scenario.write("<b>RESPONSE BODY : </b>");
		scenario.write("<pre style=\"margin-left: 5em;text-indent: 0em;\">"
				+ request.getResponseAsJson() + "</pre>");
	}

	public void checkResponseCode(int expectedResponseCode) {
		assertEquals(expectedResponseCode, request.getStatusCode());
	}

	public void checkParameter(String jsonPath, Object expectedValue) {
		Object actualValue;
		try {
			actualValue = readPathValue(jsonPath);
		} catch (PathNotFoundException exception) {
			throw new AssertionFailedError("Path to " + jsonPath + " not found");
		}
		if (actualValue == null) {
			assertNull(jsonPath + " is null", expectedValue);
		} else {
			assertEquals("Expected " + expectedValue.toString() + " but was "
					+ actualValue.toString(), expectedValue, actualValue);
		}

	}

	public void checkIfAParameterIsInArray(String jsonPath, Object expectedValue) {
		Object actualValue;
		try {
			actualValue = readPathValue(jsonPath);
		} catch (PathNotFoundException exception) {
			throw new AssertionFailedError("Path to " + jsonPath + " not found");
		}
		if (actualValue == null) {
			assertNull(jsonPath + " is null", expectedValue);
		} else if (actualValue instanceof List<?>) {
			assertTrue(jsonPath + " doesn't contains " + expectedValue,
					((List<?>) actualValue).contains(expectedValue));
		} else {
			assertEquals(jsonPath + " doesn't contains " + expectedValue,
					expectedValue, actualValue);
		}
	}

	public void checkIfParameterExists(String jsonPath) {
		try {
			readPathValue(jsonPath);
		} catch (PathNotFoundException exception) {
			throw new AssertionFailedError("Path to " + jsonPath + " not found");
		}
	}

	public void checkIfParameterDoesNotExists(String jsonPath) {
		try {
			readPathValue(jsonPath);
		} catch (PathNotFoundException exception) {
			assertTrue(exception instanceof PathNotFoundException);
		}
	}

	public void checkArrayLength(String jsonPath, int expectedLenght) {
		Object actualValue = null;
		try {
			actualValue = readPathValue(jsonPath);
		} catch (PathNotFoundException exception) {
			assertTrue("Path to " + jsonPath + " not found",
					expectedLenght == 0);
			return;
		}
		if (actualValue instanceof List<?>) {
			assertEquals(
					jsonPath + " doesn't contains "
							+ String.valueOf(expectedLenght) + " elements",
					((List<?>) actualValue).size(), expectedLenght);
		} else {
			assertTrue(jsonPath + " is a single element ", expectedLenght == 1);
		}
	}

	Object readPathValue(String jsonPath) {
		return JsonPath.read(request.getResponseAsJson(), jsonPath);
	}

	public boolean hasBeenExecuted() {
		return hasBeenExecuted;
	}
}