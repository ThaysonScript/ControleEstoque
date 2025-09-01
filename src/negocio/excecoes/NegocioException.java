package negocio.excecoes;

public class NegocioException extends Exception {
    public NegocioException() {

    }

    public NegocioException(String message) {
        super(message);
    }

    public NegocioException(String message, Throwable cause) {
        super(message, cause);
    }

}
