package negocio.excecoes;

public class DadosInvalidosException extends NegocioException {

    public DadosInvalidosException(String message) {
        super(message);
    }

    public DadosInvalidosException(String message, Throwable cause) {
        super(message, cause);
    }
}
