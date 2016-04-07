package com.jjortega.cuapi.testwrapper;

import java.util.ArrayList;
import java.util.List;

import junit.framework.AssertionFailedError;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.jjortega.cuapi.testwrapper.RequestTestStep;

public class CheckIfAParameterIsInArrayTest {

	@Before
	public void setUp() {
	}

	@Test
	public void checkStringParameterTest() {

		RequestTestStep mock = Mockito.mock(RequestTestStep.class);

		Mockito.when(mock.readPathValue(Mockito.anyString())).thenReturn("foo");

		Mockito.doCallRealMethod()
				.when(mock)
				.checkIfAParameterIsInArray(Mockito.anyString(),
						Mockito.anyObject());

		mock.checkIfAParameterIsInArray("$.foo", "foo");
	}

	@Test
	public void checkIntParameterTest() {

		RequestTestStep mock = Mockito.mock(RequestTestStep.class);

		Mockito.when(mock.readPathValue(Mockito.anyString())).thenReturn(1);

		Mockito.doCallRealMethod()
				.when(mock)
				.checkIfAParameterIsInArray(Mockito.anyString(),
						Mockito.anyObject());

		mock.checkIfAParameterIsInArray("$.foo", 1);
	}

	@Test
	public void checkBooleanParameterTest() {

		RequestTestStep mock = Mockito.mock(RequestTestStep.class);

		Mockito.when(mock.readPathValue(Mockito.anyString())).thenReturn(true);

		Mockito.doCallRealMethod()
				.when(mock)
				.checkIfAParameterIsInArray(Mockito.anyString(),
						Mockito.anyObject());

		mock.checkIfAParameterIsInArray("$.foo", true);

	}

	@Test
	public void checkNullParameterTest() {

		RequestTestStep mock = Mockito.mock(RequestTestStep.class);

		Mockito.when(mock.readPathValue(Mockito.anyString())).thenReturn(null);

		Mockito.doCallRealMethod()
				.when(mock)
				.checkIfAParameterIsInArray(Mockito.anyString(),
						Mockito.anyObject());

		mock.checkIfAParameterIsInArray("$.foo", null);

	}

	@Test(expected = AssertionFailedError.class)
	public void checkStringParameterTestJsonNull() {

		RequestTestStep mock = Mockito.mock(RequestTestStep.class);

		Mockito.when(mock.readPathValue(Mockito.anyString())).thenReturn(null);

		Mockito.doCallRealMethod()
				.when(mock)
				.checkIfAParameterIsInArray(Mockito.anyString(),
						Mockito.anyObject());

		mock.checkIfAParameterIsInArray("$.foo", "foo");

	}

	@Test(expected = AssertionFailedError.class)
	public void checkWrongStringParameterTest() {

		RequestTestStep mock = Mockito.mock(RequestTestStep.class);

		Mockito.when(mock.readPathValue(Mockito.anyString())).thenReturn("foo");

		Mockito.doCallRealMethod()
				.when(mock)
				.checkIfAParameterIsInArray(Mockito.anyString(),
						Mockito.anyObject());

		mock.checkIfAParameterIsInArray("$.foo", "bar");

	}

	@Test(expected = AssertionFailedError.class)
	public void checkWrongIntParameterTest() {

		RequestTestStep mock = Mockito.mock(RequestTestStep.class);

		Mockito.when(mock.readPathValue(Mockito.anyString())).thenReturn(1);

		Mockito.doCallRealMethod()
				.when(mock)
				.checkIfAParameterIsInArray(Mockito.anyString(),
						Mockito.anyObject());

		mock.checkIfAParameterIsInArray("$.foo", 2);

	}

	@Test(expected = AssertionFailedError.class)
	public void checkWrongBooleanParameterTest() {

		RequestTestStep mock = Mockito.mock(RequestTestStep.class);

		Mockito.when(mock.readPathValue(Mockito.anyString())).thenReturn(true);

		Mockito.doCallRealMethod()
				.when(mock)
				.checkIfAParameterIsInArray(Mockito.anyString(),
						Mockito.anyObject());

		mock.checkIfAParameterIsInArray("$.foo", false);

	}

	@Test(expected = AssertionFailedError.class)
	public void checkNotFoundParameterTest() {

		RequestTestStep mock = Mockito.mock(RequestTestStep.class);

		Mockito.doThrow(AssertionFailedError.class).when(mock)
				.readPathValue(Mockito.anyString());

		Mockito.doCallRealMethod()
				.when(mock)
				.checkIfAParameterIsInArray(Mockito.anyString(),
						Mockito.anyObject());

		mock.checkIfAParameterIsInArray("$.foo", "foo");

	}

	@Test(expected = AssertionFailedError.class)
	public void checkWrongTypeParameterTest() {

		RequestTestStep mock = Mockito.mock(RequestTestStep.class);

		Mockito.when(mock.readPathValue(Mockito.anyString())).thenReturn(true);

		Mockito.doCallRealMethod()
				.when(mock)
				.checkIfAParameterIsInArray(Mockito.anyString(),
						Mockito.anyObject());

		mock.checkIfAParameterIsInArray("$.foo", "true");

	}

	// ArrayTests

	@Test
	public void checkIfAParameterIsInStringList() {
		RequestTestStep mock = Mockito.mock(RequestTestStep.class);

		List<String> mockList = new ArrayList<String>();
		mockList.add("foo");
		mockList.add("bar");
		mockList.add("Cucumber");

		Mockito.when(mock.readPathValue(Mockito.anyString())).thenReturn(
				mockList);

		Mockito.doCallRealMethod()
				.when(mock)
				.checkIfAParameterIsInArray(Mockito.anyString(),
						Mockito.anyObject());

		mock.checkIfAParameterIsInArray("$.foo", "bar");
	}

	@Test
	public void checkIfAParameterIsInIntList() {
		RequestTestStep mock = Mockito.mock(RequestTestStep.class);

		List<Integer> mockList = new ArrayList<Integer>();
		mockList.add(1);
		mockList.add(25);
		mockList.add(75);

		Mockito.when(mock.readPathValue(Mockito.anyString())).thenReturn(
				mockList);

		Mockito.doCallRealMethod()
				.when(mock)
				.checkIfAParameterIsInArray(Mockito.anyString(),
						Mockito.anyObject());

		mock.checkIfAParameterIsInArray("$.foo", 25);
	}

	@Test
	public void checkIfAParameterIsInBooleanList() {
		RequestTestStep mock = Mockito.mock(RequestTestStep.class);

		List<Boolean> mockList = new ArrayList<Boolean>();
		mockList.add(true);
		mockList.add(true);
		mockList.add(false);

		Mockito.when(mock.readPathValue(Mockito.anyString())).thenReturn(
				mockList);

		Mockito.doCallRealMethod()
				.when(mock)
				.checkIfAParameterIsInArray(Mockito.anyString(),
						Mockito.anyObject());

		mock.checkIfAParameterIsInArray("$.foo", true);
	}

	@Test(expected = AssertionFailedError.class)
	public void checkIfAParameterIsNotInStringList() {
		RequestTestStep mock = Mockito.mock(RequestTestStep.class);

		List<String> mockList = new ArrayList<String>();
		mockList.add("foo");
		mockList.add("bar");
		mockList.add("Cucumber");

		Mockito.when(mock.readPathValue(Mockito.anyString())).thenReturn(
				mockList);

		Mockito.doCallRealMethod()
				.when(mock)
				.checkIfAParameterIsInArray(Mockito.anyString(),
						Mockito.anyObject());

		mock.checkIfAParameterIsInArray("$.foo", "something");
	}

	@Test(expected = AssertionFailedError.class)
	public void checkIfAParameterIsNotInIntList() {
		RequestTestStep mock = Mockito.mock(RequestTestStep.class);

		List<Integer> mockList = new ArrayList<Integer>();
		mockList.add(1);
		mockList.add(25);
		mockList.add(75);

		Mockito.when(mock.readPathValue(Mockito.anyString())).thenReturn(
				mockList);

		Mockito.doCallRealMethod()
				.when(mock)
				.checkIfAParameterIsInArray(Mockito.anyString(),
						Mockito.anyObject());

		mock.checkIfAParameterIsInArray("$.foo", 32);
	}

	@Test(expected = AssertionFailedError.class)
	public void checkIfAParameterIsNotInBooleanList() {
		RequestTestStep mock = Mockito.mock(RequestTestStep.class);

		List<Boolean> mockList = new ArrayList<Boolean>();
		mockList.add(true);
		mockList.add(true);
		mockList.add(true);

		Mockito.when(mock.readPathValue(Mockito.anyString())).thenReturn(
				mockList);

		Mockito.doCallRealMethod()
				.when(mock)
				.checkIfAParameterIsInArray(Mockito.anyString(),
						Mockito.anyObject());

		mock.checkIfAParameterIsInArray("$.foo", false);
	}

	@Test(expected = AssertionFailedError.class)
	public void checkIfAWrongTypeParameterIsInList() {
		RequestTestStep mock = Mockito.mock(RequestTestStep.class);

		List<Boolean> mockList = new ArrayList<Boolean>();
		mockList.add(true);
		mockList.add(true);
		mockList.add(false);

		Mockito.when(mock.readPathValue(Mockito.anyString())).thenReturn(
				mockList);

		Mockito.doCallRealMethod()
				.when(mock)
				.checkIfAParameterIsInArray(Mockito.anyString(),
						Mockito.anyObject());

		mock.checkIfAParameterIsInArray("$.foo", "true");
	}
}