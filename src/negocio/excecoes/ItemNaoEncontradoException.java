package negocio.excecoes;

public class ItemNaoEncontradoException extends NegocioException {
    public ItemNaoEncontradoException(String message) {
        super(message);
    }

    public ItemNaoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }
}
