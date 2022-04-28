package com.dronesystem.dronex.responses;

import com.dronesystem.dronex.utils.DateUtil;
import java.io.Serializable;
import java.util.Date;

import org.springframework.http.HttpStatus;

public class CustomResponse<D> implements Serializable {

	private final HttpStatus status;
	private final String code;
	private final String message;
	private final String timestamp;
	private final String description;
	private final String error;
	private final D data;

	private CustomResponse(CustomResponseBuilder<D> builder) {

		this.status = builder.status;
		this.code = builder.code;
		this.message = builder.message;
		this.timestamp = builder.timestamp;
		this.data = builder.data;
		this.description = builder.description;
		this.error = builder.error;
	}

	public HttpStatus getStatus() {
		return this.status;
	}

	public String getCode() {
		return this.code;
	}

	public String getMessage() {
		return this.message;
	}

	public String getTimestamp() {
		return this.timestamp;
	}

	public D getData() {
		return data;
	}

	public static final class CustomResponseBuilder<D> {
		private HttpStatus status;
		private String code;
		private String message;
		private String description;
		private String timestamp;
		private String error;
		private D data;

		public CustomResponseBuilder<D> withStatus(HttpStatus status) {
			this.status = status;
			return this;
		}

		public CustomResponseBuilder<D> withCode(String code) {
			this.code = code;
			return this;
		}

		public CustomResponseBuilder<D> withMessage(String message) {
			this.message = message;
			return this;
		}

		public CustomResponseBuilder<D> withDetail(String detail) {
			this.description = detail;
			return this;
		}

		public CustomResponseBuilder<D> withError(String error) {
			this.error = error;
			return this;
		}

		public CustomResponseBuilder<D> withTimestamp(Date timestamp) {
			System.out.println("Date value==>" + timestamp);
			this.timestamp = DateUtil.convertToText(timestamp);
			System.out.println("converted timestamp===>" + this.timestamp);
			return this;
		}

		public CustomResponseBuilder<D> withData(D data) {
			this.data = data;
			return this;
		}

		public CustomResponse<D> build() {
			return new CustomResponse<D>(this);
		}
	}
}
