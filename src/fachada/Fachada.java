package fachada;

import negocio.entidade.*;
import negocio.excecoes.CategoriaEmUsoException;
import negocio.excecoes.CategoriaNaoEncontradaException;
import negocio.excecoes.DadosInvalidosException;
import negocio.excecoes.NegocioException;
import negocio.repositorio.*;

import java.util.List;
import java.util.Objects;

public class Fachada {
    private static Fachada instance;
    private final IRepositorioProdutos repositorioProdutos;
    private final IRepositorioCategorias repositorioCategorias;

    private Fachada() {
        this.repositorioProdutos = new RepositorioProdutosMemoria();
        this.repositorioCategorias = new RepositorioCategoriasMemoria();
    }

    public static Fachada getInstance() {
        if (instance == null) {
            instance = new Fachada();
        }
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

    public Relatorio gerarRelatorioEstoqueBaixo() {
        GeradorRelatorioEstoqueBaixo gerador = new GeradorRelatorioEstoqueBaixo(this.repositorioProdutos);
        return gerador.gerar();
    }

    public Relatorio gerarRelatorioProdutosAVencer() {
        GeradorRelatorioProdutosAVencer gerador = new GeradorRelatorioProdutosAVencer(this.repositorioProdutos);
        return gerador.gerar();
    }

    public Relatorio gerarRelatorioEstoqueAbaixoDe(int limite) {
        GeradorRelatorioEstoqueFiltrado gerador = new GeradorRelatorioEstoqueFiltrado(this.repositorioProdutos, limite);
        return gerador.gerar();
    }
}