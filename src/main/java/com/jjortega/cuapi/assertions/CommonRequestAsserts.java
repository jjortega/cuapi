package com.jjortega.cuapi.assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.jjortega.cuapi.container.CommonContainer;

public class CommonRequestAsserts {

	private CommonContainer container;

	public CommonRequestAsserts(CommonContainer container) {
		super();
		this.container = container;
	}

	public void checkTheHttpResponseIs(int expectedResponseCode) {
		container.getLastRunRequest().checkResponseCode(expectedResponseCode);
	}

	public void checkTheNumberOfResultsIs(String naturalPathSentence,
			int expectedLenght) {
		container.getLastRunRequest().checkArrayLength(
				fromNaturalToJsonPath(naturalPathSentence), expectedLenght);
	}

	public void checkParameterIs(String naturalPathSentence,
			Object expectedValue) {
		container.getLastRunRequest().checkParameter(
				fromNaturalToJsonPath(naturalPathSentence), expectedValue);
	}

	public void checkAnyParameterIs(String parameter,
			String naturalPathSentence, Object expectedValue) {
		container.getLastRunRequest().checkIfAParameterIsInArray(
				fromNaturalToJsonPath(naturalPathSentence), expectedValue);
	}

	public void checkParameterExists(String naturalPathSentence) {
		container.getLastRunRequest().checkIfParameterExists(
				fromNaturalToJsonPath(naturalPathSentence));
	}

	public void checkParameterDoesNotExists(String naturalPathSentence) {
		container.getLastRunRequest().checkIfParameterDoesNotExists(
				fromNaturalToJsonPath(naturalPathSentence));
	}

	public static String fromNaturalToJsonPath(String naturalSentence) {
		String[] parameters = naturalSentence.split(" in ");
		ArrayUtils.reverse(parameters);
		List<String> tree = new ArrayList<String>();
		for (String paramHelper : Arrays.asList(parameters)) {
			for (String s : paramHelper.split(" ")) {
				tree.add(s);
			}
		}
		String jsonPath = "$";
		for (String elementTree : tree) {
			if (StringUtils.isNumeric(elementTree) || elementTree.equals("*")) {
				jsonPath = jsonPath + "[" + elementTree + "]";
			} else {
				jsonPath = jsonPath + "." + elementTree;
			}
		}
		return jsonPath;
	}

}
