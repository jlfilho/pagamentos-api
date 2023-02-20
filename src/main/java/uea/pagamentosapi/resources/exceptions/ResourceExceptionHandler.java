package uea.pagamentosapi.resources.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import uea.pagamentosapi.services.exceptions.ObjectNotFountException;
import uea.pagamentosapi.services.exceptions.PessoaInativaException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(ObjectNotFountException.class)
	public ResponseEntity<StandardError> objectNotFountException(ObjectNotFountException e,
			HttpServletRequest resquest) {
		List<String> errors = Arrays
				.asList(messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale()));
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), errors, e.getMessage(),
				resquest.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<StandardError> httpMessageNotReadableException(HttpMessageNotReadableException e,
			HttpServletRequest resquest) {
		List<String> errors = Arrays
				.asList(messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale()));
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), errors, e.getMessage(),
				resquest.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> methodArgumentNotValidException(MethodArgumentNotValidException e,
			HttpServletRequest resquest) {
		List<String> errors = criarListaDeErros(e.getBindingResult());
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError err = new StandardError(Instant.now(), status.value(), errors, e.getMessage(),
				resquest.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(EmptyResultDataAccessException.class)
	public ResponseEntity<StandardError> emptyResultDataAccessException(EmptyResultDataAccessException e,
			HttpServletRequest resquest) {
		List<String> errors = Arrays
				.asList(messageSource.getMessage("recurso.indisponivel", null, LocaleContextHolder.getLocale()));
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), errors, e.getMessage(),
				resquest.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFoundException(EntityNotFoundException e,
			HttpServletRequest resquest) {
		List<String> errors = Arrays
				.asList(messageSource.getMessage("recurso.indisponivel", null, LocaleContextHolder.getLocale()));
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), errors, e.getMessage(),
				resquest.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException e,
			HttpServletRequest resquest) {
		List<String> errors = Arrays
				.asList(messageSource.getMessage("recurso.operacao-nao-permitida", null, LocaleContextHolder.getLocale()));
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), errors, e.getMessage(),
				resquest.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(PessoaInativaException.class)
	public ResponseEntity<StandardError> pessoaInativaException(PessoaInativaException e,
			HttpServletRequest resquest) {
		List<String> errors = Arrays
				.asList(messageSource.getMessage("pessoa.inativa", null, LocaleContextHolder.getLocale()));
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(Instant.now(), status.value(), errors, e.getMessage(),
				resquest.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	

	private List<String> criarListaDeErros(BindingResult bindingResult) {
		List<String> erros = new ArrayList<>();

		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			erros.add(mensagemUsuario);
		}

		return erros;
	}

}
