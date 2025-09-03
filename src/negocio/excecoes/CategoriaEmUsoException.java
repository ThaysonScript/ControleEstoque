package negocio.excecoes;

public class CategoriaEmUsoException extends NegocioException {
    public CategoriaEmUsoException(String categoriaNome, String produtoNome) {
        super("A categoria '" + categoriaNome + "' não pode ser removida pois está em uso pelo produto '" + produtoNome + "'.");
    }

    public CategoriaEmUsoException(String message) {
        super(message);
    }

    public CategoriaEmUsoException(String message, Throwable cause) {
        super(message, cause);
    }
}