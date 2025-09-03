package fachada;

import negocio.entidade.categoria.Categoria;
import negocio.entidade.produto.Produto;
import negocio.excecoes.CategoriaEmUsoException;
import negocio.excecoes.CategoriaNaoEncontradaException;
import negocio.excecoes.DadosInvalidosException;
import negocio.excecoes.NegocioException;
import negocio.repositorio.IRepositorioCategorias;
import negocio.repositorio.IRepositorioProdutos;
import negocio.repositorio.RepositorioCategoriasMemoria;
import negocio.repositorio.RepositorioProdutosMemoria;

import java.util.List;
import java.util.Objects;

public class CategoriaFachada {
    private static CategoriaFachada instance;
    private final IRepositorioProdutos repositorioProdutos;
    private final IRepositorioCategorias repositorioCategorias;

    private CategoriaFachada(IRepositorioProdutos repoProdutos, IRepositorioCategorias repoCategorias) {
        this.repositorioProdutos = repoProdutos;
        this.repositorioCategorias = repoCategorias;
    }

    public static void init(IRepositorioProdutos repoProdutos, IRepositorioCategorias repoCategorias) {
        if (instance == null) {
            instance = new CategoriaFachada(repoProdutos, repoCategorias);
        }
    }

    public static CategoriaFachada getInstance() {
        return instance;
    }

    public void cadastrarCategoria(Categoria categoria) throws DadosInvalidosException {
        if (this.repositorioCategorias.buscarPorNome(categoria.getNome()) != null) {
            throw new DadosInvalidosException("Já existe uma categoria com o nome '" + categoria.getNome() + "'.");
        }
        this.repositorioCategorias.salvar(categoria);
    }

    public void atualizarCategoria(Categoria categoria) throws DadosInvalidosException, CategoriaNaoEncontradaException {
        Categoria categoriaExistenteComMesmoNome = this.repositorioCategorias.buscarPorNome(categoria.getNome());
        if (categoriaExistenteComMesmoNome != null && !Objects.equals(categoriaExistenteComMesmoNome.getId(), categoria.getId())) {
            throw new DadosInvalidosException("Já existe outra categoria com o nome '" + categoria.getNome() + "'.");
        }
        if (this.repositorioCategorias.buscarPorId(categoria.getId()) == null) {
            throw new CategoriaNaoEncontradaException(categoria.getId().toString());
        }
        this.repositorioCategorias.salvar(categoria);
    }

    public void removerCategoria(Integer id) throws NegocioException {
        Categoria categoriaParaRemover = this.buscarCategoriaPorId(id);
        if (categoriaParaRemover == null) {
            throw new CategoriaNaoEncontradaException(id.toString());
        }
        for (Produto produto : this.repositorioProdutos.listarTodos()) {
            if (produto.getCategoria() != null && Objects.equals(produto.getCategoria().getId(), id)) {
                throw new CategoriaEmUsoException(categoriaParaRemover.getNome(), produto.getNome());
            }
        }
        this.repositorioCategorias.remover(id);
    }

    public Categoria buscarCategoriaPorId(int id) {
        return this.repositorioCategorias.buscarPorId(id);
    }

    public Categoria buscarCategoriaPorNome(String nome) {
        return this.repositorioCategorias.buscarPorNome(nome);
    }

    public List<Categoria> listarTodasCategorias() {
        return this.repositorioCategorias.listarTodas();
    }
}
