package com.jjortega.cuapi.testwrapper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.jjortega.cuapi.testwrapper.RequestTestStep;

public class CheckArrayLengthTest {

	@Before
	public void setUp() {
	}

	@Test
	public void checkStringParameterTest() {

		RequestTestStep mock = Mockito.mock(RequestTestStep.class);

		Mockito.when(mock.readPathValue(Mockito.anyString())).thenReturn("foo");

		Mockito.doCallRealMethod().when(mock)
				.checkArrayLength(Mockito.anyString(), Mockito.anyInt());

		mock.checkArrayLength("$.foo", 1);

	}

	@Test
	public void checkIntParameterTest() {

		RequestTestStep mock = Mockito.mock(RequestTestStep.class);

		Mockito.when(mock.readPathValue(Mockito.anyString())).thenReturn(1);

		Mockito.doCallRealMethod().when(mock)
				.checkArrayLength(Mockito.anyString(), Mockito.anyInt());

		mock.checkArrayLength("$.foo", 1);
	}

	@Test
	public void checkBooleanParameterTest() {

		RequestTestStep mock = Mockito.mock(RequestTestStep.class);

		Mockito.when(mock.readPathValue(Mockito.anyString())).thenReturn(true);

		Mockito.doCallRealMethod().when(mock)
				.checkArrayLength(Mockito.anyString(), Mockito.anyInt());

		mock.checkArrayLength("$.foo", 1);

	}

	@Test(expected = AssertionError.class)
	public void checkWrongSizeStringParameterTest() {

		RequestTestStep mock = Mockito.mock(RequestTestStep.class);

		Mockito.when(mock.readPathValue(Mockito.anyString())).thenReturn("foo");

		Mockito.doCallRealMethod().when(mock)
				.checkArrayLength(Mockito.anyString(), Mockito.anyInt());

		mock.checkArrayLength("$.foo", 2);

	}
	//
	// @Test(expected = AssertionFailedError.class)
	// public void checkWrongIntParameterTest() {
	//
	// RequestTestStep mock = Mockito.mock(RequestTestStep.class);
	//
	// Mockito.when(mock.readPathValue(Mockito.anyString())).thenReturn(1);
	//
	// Mockito.doCallRealMethod().when(mock)
	// .checkArrayLength(Mockito.anyString(), Mockito.anyInt());
	//
	// mock.checkArrayLength("$.foo", 2);
	//
	// }
	//
	// @Test(expected = AssertionFailedError.class)
	// public void checkWrongBooleanParameterTest() {
	//
	// RequestTestStep mock = Mockito.mock(RequestTestStep.class);
	//
	// Mockito.when(mock.readPathValue(Mockito.anyString())).thenReturn(true);
	//
	// Mockito.doCallRealMethod().when(mock)
	// .checkArrayLength(Mockito.anyString(), Mockito.anyInt());
	//
	// mock.checkArrayLength("$.foo", false);
	//
	// }
	//
	// @Test(expected = AssertionFailedError.class)
	// public void checkNotFoundParameterTest() {
	//
	// RequestTestStep mock = Mockito.mock(RequestTestStep.class);
	//
	// Mockito.doThrow(AssertionFailedError.class).when(mock)
	// .readPathValue(Mockito.anyString());
	//
	// Mockito.doCallRealMethod().when(mock)
	// .checkArrayLength(Mockito.anyString(), Mockito.anyInt());
	//
	// mock.checkArrayLength("$.foo", "foo");
	//
	// }
	//
	// @Test(expected = AssertionFailedError.class)
	// public void checkWrongTypeParameterTest() {
	//
	// RequestTestStep mock = Mockito.mock(RequestTestStep.class);
	//
	// Mockito.when(mock.readPathValue(Mockito.anyString())).thenReturn(true);
	//
	// Mockito.doCallRealMethod().when(mock)
	// .checkArrayLength(Mockito.anyString(), Mockito.anyInt());
	//
	// mock.checkArrayLength("$.foo", "true");
	//
	// }
}