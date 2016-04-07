package com.jjortega.cuapi.requestcore;

import java.lang.reflect.Field;

import com.jjortega.cuapi.annotations.Parameter;
import com.jjortega.cuapi.annotations.Resource;

public class RequestFactory {

	public static void buildRequest(CommonRequest request) {
		UriResolverBuilder uriResolverBuilder = new UriResolverBuilder();
		Class<? extends CommonRequest> clazz = request.getClass();
		for (Field field : clazz.getDeclaredFields()) {
			Parameter parameter = field.getDeclaredAnnotation(Parameter.class);
			field.setAccessible(true);
			try {
				String value = (String) field.get(request);
				uriResolverBuilder.addParam(parameter.name(), value);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String resource = uriResolverBuilder.build().resolve(
				clazz.getAnnotation(Resource.class).value());
		request.setResource(resource);
	}

}
