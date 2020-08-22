package com.bidding.system.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {

	private final ResponseTypeEnum status;

	@JsonCreator
	public ErrorResponse(@JsonProperty("status") ResponseTypeEnum status) {
		this.status = status;
	}

	public ResponseTypeEnum getStatus() {
		return status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ErrorResponse [status=").append(status).append("]");
		return builder.toString();
	}

}
