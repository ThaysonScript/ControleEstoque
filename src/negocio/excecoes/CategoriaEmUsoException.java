package negocio.excecoes;

public class CategoriaEmUsoException extends NegocioException {
    public CategoriaEmUsoException(String categoriaNome, String produtoNome) {
        super();
    }

    public CategoriaEmUsoException(String message) {
        super(message);
    }

    public CategoriaEmUsoException(String message, Throwable cause) {
        super(message, cause);
    }
}