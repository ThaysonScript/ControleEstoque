package negocio.repositorio;

import negocio.entidade.produto.Produto;

import java.util.List;

public interface IRepositorioProdutos {
    void salvar(Produto produto);
    Produto buscarPorId(int id);
    Produto buscarPorNome(String nome);
    boolean remover(int id);
    List<Produto> listarTodos();
    List<Produto> listarEstoqueBaixo();
    List<Produto> listarAbaixoDe(int limite);
}