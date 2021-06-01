package com.fei.projetodecantadorbe.responseAPI;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Validated
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiErrorResponse {

	@JsonIgnore
	@JsonProperty("code")
	private String code = null;

	@JsonProperty("message")
	private String message = null;

	@JsonProperty("description")
	private String description = null;

	@JsonProperty("errors")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<ErrorResponse> errors = null;

	@JsonProperty("businessNotifications")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<WarningResponse> businessNotifications = null;


    public ApiErrorResponse() {
    	super();
		this.errors = new ArrayList<>();
		this.businessNotifications = new ArrayList<>();
    }

	public ApiErrorResponse(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public ApiErrorResponse(String message) {
		this.message = message;
	}

	public ApiErrorResponse(String code, String message, String description,
							List<ErrorResponse> errors) {
		this.code = code;
		this.message = message;
		this.description = description;
		this.errors = errors;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ErrorResponse> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorResponse> errors) {
		this.errors = errors;
	}

	public List<WarningResponse> getBusinessNotifications() {
		return businessNotifications;
	}

	public void setBusinessNotifications(List<WarningResponse> businessNotifications) {
		this.businessNotifications = businessNotifications;
	}

}