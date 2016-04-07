package com.jjortega.cuapi.requestcore;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class UriResolver {

	private Map<String, Object> map;

	public UriResolver() {
		this.map = new HashMap<String, Object>();
	}

	public String resolve(String unresolvedUri) {
		String realUri = unresolvedUri;
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getValue() == null) {
				realUri = realUri.replaceFirst("\\{:" + entry.getKey() + "\\}",
						"");
				realUri = realUri.replaceFirst("(&)*" + entry.getKey() + "=",
						"");
			} else {
				realUri = realUri.replaceFirst("\\{:" + entry.getKey() + "\\}",
						(String) entry.getValue());
				realUri = realUri.replaceFirst(entry.getKey() + "=",
						entry.getKey() + "=" + (String) entry.getValue());
			}
		}
		return realUri;
	}

	public void addParam(String key, Object value) {
		for (Entry<String, Object> entry : map.entrySet()) {
			if (entry.getKey().equalsIgnoreCase(key)) {
				return;
			}
		}
		map.put(key, value);
	}
}
