package negocio.repositorio;

import negocio.entidade.Categoria;
import negocio.excecoes.CategoriaNaoEncontradaException;

import java.util.List;

public interface IRepositorioCategorias {
    void salvar(Categoria categoria);
    Categoria buscarPorId(int id);
    Categoria buscarPorNome(String nome);
    void remover(Integer id) throws CategoriaNaoEncontradaException;
    List<Categoria> listarTodas();
}