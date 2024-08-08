package com.bharath.usermanagement.exception;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorInformation {
	private Integer errorCode;
	private String errorMessage;
	private LocalDateTime occuredAt;
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public LocalDateTime getOccuredAt() {
		return occuredAt;
	}
	public void setOccuredAt(LocalDateTime occuredAt) {
		this.occuredAt = occuredAt;
	}

	


}