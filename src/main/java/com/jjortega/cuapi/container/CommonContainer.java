package com.jjortega.cuapi.container;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.jjortega.cuapi.testwrapper.RequestTestStep;

public class CommonContainer {

	private List<RequestTestStep> requestTestStepsList = new ArrayList<RequestTestStep>();

	public RequestTestStep getLastRunRequest() {
		if(requestTestStepsList.size() > 0) {
			for (int index = requestTestStepsList.size() - 1; index >= 0; index--) {			
				if(requestTestStepsList.get(index).hasBeenExecuted()) {
					return requestTestStepsList.get(index);
				}
			}
		}
		return null;
	}

	public void setRequestToList(RequestTestStep requestTestStep) {
		requestTestStepsList.add(requestTestStep);
	}

	public RequestTestStep getCurrentRequest() {
		return requestTestStepsList.get(requestTestStepsList.size() - 1);
	}
}
