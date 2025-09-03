package fachada;

import negocio.entidade.produto.Produto;
import negocio.excecoes.DadosInvalidosException;
import negocio.excecoes.NegocioException;
import negocio.repositorio.IRepositorioCategorias;
import negocio.repositorio.IRepositorioProdutos;
import negocio.repositorio.RepositorioCategoriasMemoria;
import negocio.repositorio.RepositorioProdutosMemoria;

import java.util.List;

public class ProdutoFachada {
    private static ProdutoFachada instance;
    private final IRepositorioProdutos repositorioProdutos;
    private final IRepositorioCategorias repositorioCategorias;

    // Construtor agora recebe as dependências
    public ProdutoFachada(IRepositorioProdutos repoProdutos, IRepositorioCategorias repoCategorias) {
        this.repositorioProdutos = repoProdutos;
        this.repositorioCategorias = repoCategorias;
    }

    public static void init(IRepositorioProdutos repoProdutos, IRepositorioCategorias repoCategorias) {
        if (instance == null) {
            instance = new ProdutoFachada(repoProdutos, repoCategorias);
        }
    }

    public static ProdutoFachada getInstance() {
        return instance;
    }

    public void cadastrarProduto(Produto produto) throws NegocioException {
        if (produto.getCategoria() == null || this.repositorioCategorias.buscarPorId(produto.getCategoria().getId()) == null) {
            throw new DadosInvalidosException("A categoria associada ao produto não existe.");
        }
        this.repositorioProdutos.salvar(produto);
    }

    public void atualizarProduto(Produto produto) throws NegocioException {
        if (this.repositorioProdutos.buscarPorId(produto.getId()) == null) {
            throw new NegocioException("Produto com ID " + produto.getId() + " não encontrado para atualização.");
        }
        if (produto.getCategoria() == null || this.repositorioCategorias.buscarPorId(produto.getCategoria().getId()) == null) {
            throw new DadosInvalidosException("A categoria associada ao produto não existe.");
        }
        this.repositorioProdutos.salvar(produto);
    }

    public void removerProduto(int id) throws NegocioException {
        this.repositorioProdutos.remover(id);
    }

    public Produto buscarProdutoPorId(int id) {
        return this.repositorioProdutos.buscarPorId(id);
    }

    public Produto buscarProdutoPorNome(String nome) {
        return this.repositorioProdutos.buscarPorNome(nome);
    }

    public List<Produto> listarTodosProdutos() {
        return this.repositorioProdutos.listarTodos();
    }
}
