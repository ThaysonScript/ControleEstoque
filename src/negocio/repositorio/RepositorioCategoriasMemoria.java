package negocio.repositorio;

import negocio.entidade.Categoria;
import negocio.excecoes.CategoriaNaoEncontradaException;

import java.util.ArrayList;
import java.util.List;

public class RepositorioCategoriasMemoria implements IRepositorioCategorias {
    private final List<Categoria> categorias;
    private static int proximoId = 1;

    public RepositorioCategoriasMemoria() {
        this.categorias = new ArrayList<>();
    }

    @Override
    public void salvar(Categoria categoria) {
        if (categoria.getId() == 0) {
            categoria.setId(proximoId++);
            this.categorias.add(categoria);
        } else {
            this.categorias.removeIf(c -> c.getId() == categoria.getId());
            this.categorias.add(categoria);
        }
    }

    @Override
    public Categoria buscarPorId(int id) {
        return this.categorias.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Categoria buscarPorNome(String nome) {
        return this.categorias.stream().filter(c -> c.getNome().equalsIgnoreCase(nome)).findFirst().orElse(null);
    }

    @Override
    public void remover(Integer id) throws CategoriaNaoEncontradaException {
        boolean removido = this.categorias.removeIf(c -> c.getId() == id);
        if (!removido) {
            throw new CategoriaNaoEncontradaException(id.toString());
        }
    }

    @Override
    public List<Categoria> listarTodas() {
        return new ArrayList<>(this.categorias);
    }
}