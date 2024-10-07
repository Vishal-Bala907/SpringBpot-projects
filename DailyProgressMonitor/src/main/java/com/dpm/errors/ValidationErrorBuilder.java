package com.dpm.errors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import com.dpm.modals.SaveStatus;

@Component
public class ValidationErrorBuilder {

//	@Autowired
	private SaveStatus saveStatus;

	public SaveStatus fromBindingErrors(Errors errors) {
		ValidationErrors error = new ValidationErrors("Validation failed. " + errors.getErrorCount() + " error(s)");
		for (ObjectError objectError : errors.getAllErrors()) {
			error.addValidationErrors(objectError.getDefaultMessage());
		}
		saveStatus = new SaveStatus();
		saveStatus.setErrors(error);
		saveStatus.setMessage("NOT SUCCESS");
		saveStatus.setStatus(HttpStatus.BAD_REQUEST);
		return saveStatus;
	}
}
