package com.nelioalves.cursomc.resources.util;

import java.net.URI;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class URIUtil {
	
	public static URI buildURI(Object obj, String pathVariable, int attr) {
		return ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path(String.format("/{%s}", pathVariable))
				.buildAndExpand(attr)
				.toUri();
	}
}