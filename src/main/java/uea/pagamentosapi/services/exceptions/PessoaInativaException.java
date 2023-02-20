package uea.pagamentosapi.services.exceptions;

public class PessoaInativaException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PessoaInativaException(Object codigo) {
		super("Pessoa inativa. código " + codigo);
	}

}
