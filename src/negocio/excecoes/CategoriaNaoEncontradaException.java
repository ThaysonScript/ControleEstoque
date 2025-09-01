package negocio.excecoes;

public class CategoriaNaoEncontradaException extends NegocioException {
    public CategoriaNaoEncontradaException(String message) {
        super(message);
    }

    public CategoriaNaoEncontradaException(String message, Throwable cause) {
        super(message, cause);
    }
}
