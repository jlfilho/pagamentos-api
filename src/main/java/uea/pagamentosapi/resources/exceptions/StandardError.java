package uea.pagamentosapi.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss", timezone = "GMT")
	private Instant timestamp;
	private Integer status;
	private List<String> errors;
	private String message;
	private String path;
	
	public StandardError() {
		
	}

	public StandardError(Instant timestamp, Integer status, List<String> errors, String message, String path) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.errors = errors;
		this.message = message;
		this.path = path;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setError(List<String> errors) {
		this.errors = errors;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
}

