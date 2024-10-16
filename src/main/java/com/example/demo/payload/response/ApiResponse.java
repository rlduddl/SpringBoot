package com.example.demo.payload.response;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
// JSON ?쇰줈 由ы꽩?좊븣 ?ㅼ쓽 媛믪씠 null?대㈃ 誘명룷?⑥떆?ㅻ뒗 ?대끂?뚯씠??
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {
	
	private String data;
	private boolean success;
	private String timestamp;
	private String cause;
	private String path;
	
	public ApiResponse(boolean success, String data) {
		this.timestamp = Instant.now().toString();
		this.success = success;
		this.data = data;
		this.cause = null;
		this.path = null;
	}
	
	public ApiResponse(boolean success, String data, String cause, String path) {
		this.timestamp = Instant.now().toString();
		this.success = success;
		this.data = data;
		this.cause = cause;
		this.path = path;
	}
	
}
