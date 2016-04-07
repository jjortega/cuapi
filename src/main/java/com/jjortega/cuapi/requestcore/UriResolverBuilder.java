package com.jjortega.cuapi.requestcore;

public class UriResolverBuilder {

	private UriResolver uriResolver;

	public UriResolverBuilder() {
		 uriResolver = new UriResolver();
	}
	 
	public UriResolverBuilder addParam(String key, Object value) {
		 this.uriResolver.addParam(key, value);
		 return this;
	}
	
	public UriResolver build() {
		return this.uriResolver;
	}
}
