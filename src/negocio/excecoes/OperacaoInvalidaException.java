package negocio.excecoes;

public class OperacaoInvalidaException extends NegocioException {

    public OperacaoInvalidaException(String message) {
        super(message);
    }

    public OperacaoInvalidaException(String message, Throwable cause) {
        super(message, cause);
    }
}