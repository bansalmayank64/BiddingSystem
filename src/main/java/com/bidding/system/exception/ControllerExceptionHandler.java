package com.bidding.system.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bidding.system.dto.ErrorResponse;
import com.bidding.system.dto.ResponseTypeEnum;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler({ IllegalArgumentException.class, HttpMessageNotReadableException.class,
			IllegalStateException.class, NoSuchUserException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse handleBadRequest(Exception e) {
		return new ErrorResponse(ResponseTypeEnum.BAD_REQUEST);
	}

	@ExceptionHandler({ MissingRequestHeaderException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse handleMissingHeader(Exception e) {
		return new ErrorResponse(ResponseTypeEnum.HEADER_MISSING);
	}

	@ExceptionHandler({ MethodArgumentNotValidException.class, BindException.class, PropertyReferenceException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse handleValidationError(Exception e) {
		return new ErrorResponse(ResponseTypeEnum.VALIDATION_ERROR);
	}

	@ExceptionHandler({ AuctionNotFoundException.class })
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorResponse handleNotFoundError(Exception e) {
		return new ErrorResponse(ResponseTypeEnum.NOT_FOUND);
	}

	@ExceptionHandler({ BidNotAcceptedException.class })
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	@ResponseBody
	public ErrorResponse handleNotAcceptableError(Exception e) {
		return new ErrorResponse(ResponseTypeEnum.BID_NOT_ACCEPTED);
	}

	@ExceptionHandler({ DataIntegrityViolationException.class, Exception.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorResponse handleInternalServerError(Exception e) {
		return new ErrorResponse(ResponseTypeEnum.INTERNAL_SERVER_ERROR);
	}
}
