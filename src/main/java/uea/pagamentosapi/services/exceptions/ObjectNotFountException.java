package uea.pagamentosapi.services.exceptions;

public class ObjectNotFountException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ObjectNotFountException(Object codigo) {
		super("Recurso não encontrado. código " + codigo);
	}

}

