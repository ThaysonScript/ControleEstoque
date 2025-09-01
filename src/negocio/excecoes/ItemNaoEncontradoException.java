package negocio.excecoes;

public class ItemNaoEncontradoException extends RuntimeException {
    public ItemNaoEncontradoException(String message) {
        super(message);
    }

    public ItemNaoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }
}
