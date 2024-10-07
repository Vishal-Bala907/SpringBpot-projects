package com.dpm.modals;

import org.springframework.http.HttpStatus;

import com.dpm.errors.ValidationErrors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SaveStatus {
	private String message;
	private HttpStatus status;
	private ValidationErrors errors;
}
