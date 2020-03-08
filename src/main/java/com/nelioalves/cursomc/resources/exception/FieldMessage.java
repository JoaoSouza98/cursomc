package com.nelioalves.cursomc.resources.exception;

import java.io.Serializable;

public final class FieldMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public String name;
	public String message;

	public FieldMessage(String name, String message) {
		super();
		this.name = name;
		this.message = message;
	}
}
