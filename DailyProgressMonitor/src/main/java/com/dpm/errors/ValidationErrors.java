package com.dpm.errors;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrors {

	private List<String> errors = new ArrayList<>();
	private String errorMessage;

	public ValidationErrors() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ValidationErrors(String errorMessage) {
		super();
		this.errorMessage = errorMessage;
	}

	public void addValidationErrors(String error) {
		errors.add(error);
	}

	public List<String> getErrors() {
		return errors;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
