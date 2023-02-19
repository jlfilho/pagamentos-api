package uea.pagamentosapi.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(Object codigo) {
		super("Recurso não encontrado. código " + codigo);
	}

}

