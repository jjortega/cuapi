package com.jjortega.cuapi.requestcore;

import java.util.HashMap;
import java.util.Map;

import com.jjortega.cuapi.entity.Entity;

public class ResponseMap {

	private Map<Integer, Class<? extends Entity>> responseMap = new HashMap<Integer, Class<? extends Entity>>();

	public void addResponse(int httpStatus,
			Class<? extends Entity> responseModel) {
		this.responseMap.put(httpStatus, responseModel);
	}

	public Class<? extends Entity> getResponse(int httpStatusCode) {
		return responseMap.get(httpStatusCode);
	}
}
