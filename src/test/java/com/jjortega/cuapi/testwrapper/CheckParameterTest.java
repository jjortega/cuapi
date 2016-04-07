package com.jjortega.cuapi.testwrapper;

import junit.framework.AssertionFailedError;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.jjortega.cuapi.testwrapper.RequestTestStep;

public class CheckParameterTest {

	@Before
	public void setUp() {
	}

	@Test
	public void checkStringParameterTest() {

		RequestTestStep mock = Mockito.mock(RequestTestStep.class);

		Mockito.when(mock.readPathValue(Mockito.anyString())).thenReturn("foo");

		Mockito.doCallRealMethod().when(mock)
				.checkParameter(Mockito.anyString(), Mockito.anyObject());

		mock.checkParameter("$.foo", "foo");

	}

	@Test
	public void checkIntParameterTest() {

		RequestTestStep mock = Mockito.mock(RequestTestStep.class);

		Mockito.when(mock.readPathValue(Mockito.anyString())).thenReturn(1);

		Mockito.doCallRealMethod().when(mock)
				.checkParameter(Mockito.anyString(), Mockito.anyObject());

		mock.checkParameter("$.foo", 1);
	}

	@Test
	public void checkBooleanParameterTest() {

		RequestTestStep mock = Mockito.mock(RequestTestStep.class);

		Mockito.when(mock.readPathValue(Mockito.anyString())).thenReturn(true);

		Mockito.doCallRealMethod().when(mock)
				.checkParameter(Mockito.anyString(), Mockito.anyObject());

		mock.checkParameter("$.foo", true);

	}

	@Test
	public void checkNullParameterTest() {

		RequestTestStep mock = Mockito.mock(RequestTestStep.class);

		Mockito.when(mock.readPathValue(Mockito.anyString())).thenReturn(null);

		Mockito.doCallRealMethod().when(mock)
				.checkParameter(Mockito.anyString(), Mockito.anyObject());

		mock.checkParameter("$.foo", null);

	}

	@Test(expected = AssertionFailedError.class)
	public void checkStringParameterTestJsonNull() {

		RequestTestStep mock = Mockito.mock(RequestTestStep.class);

		Mockito.when(mock.readPathValue(Mockito.anyString())).thenReturn(null);

		Mockito.doCallRealMethod().when(mock)
				.checkParameter(Mockito.anyString(), Mockito.anyObject());

		mock.checkParameter("$.foo", "foo");

	}

	@Test(expected = AssertionFailedError.class)
	public void checkWrongStringParameterTest() {

		RequestTestStep mock = Mockito.mock(RequestTestStep.class);

		Mockito.when(mock.readPathValue(Mockito.anyString())).thenReturn("foo");

		Mockito.doCallRealMethod().when(mock)
				.checkParameter(Mockito.anyString(), Mockito.anyObject());

		mock.checkParameter("$.foo", "bar");

	}

	@Test(expected = AssertionFailedError.class)
	public void checkWrongIntParameterTest() {

		RequestTestStep mock = Mockito.mock(RequestTestStep.class);

		Mockito.when(mock.readPathValue(Mockito.anyString())).thenReturn(1);

		Mockito.doCallRealMethod().when(mock)
				.checkParameter(Mockito.anyString(), Mockito.anyObject());

		mock.checkParameter("$.foo", 2);

	}

	@Test(expected = AssertionFailedError.class)
	public void checkWrongBooleanParameterTest() {

		RequestTestStep mock = Mockito.mock(RequestTestStep.class);

		Mockito.when(mock.readPathValue(Mockito.anyString())).thenReturn(true);

		Mockito.doCallRealMethod().when(mock)
				.checkParameter(Mockito.anyString(), Mockito.anyObject());

		mock.checkParameter("$.foo", false);

	}

	@Test(expected = AssertionFailedError.class)
	public void checkNotFoundParameterTest() {

		RequestTestStep mock = Mockito.mock(RequestTestStep.class);

		Mockito.doThrow(AssertionFailedError.class).when(mock)
				.readPathValue(Mockito.anyString());

		Mockito.doCallRealMethod().when(mock)
				.checkParameter(Mockito.anyString(), Mockito.anyObject());

		mock.checkParameter("$.foo", "foo");

	}

	@Test(expected = AssertionFailedError.class)
	public void checkWrongTypeParameterTest() {

		RequestTestStep mock = Mockito.mock(RequestTestStep.class);

		Mockito.when(mock.readPathValue(Mockito.anyString())).thenReturn(true);

		Mockito.doCallRealMethod().when(mock)
				.checkParameter(Mockito.anyString(), Mockito.anyObject());

		mock.checkParameter("$.foo", "true");

	}
}