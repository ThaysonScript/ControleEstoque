package negocio.repositorio;

import negocio.entidade.Produto;

import java.util.List;

public interface IRepositorioProdutos {
    void salvar(Produto produto);
    Produto buscarPorId(int id);
    boolean remover(int id);
    List<Produto> listarTodos();
    List<Produto> listarEstoqueBaixo();
}