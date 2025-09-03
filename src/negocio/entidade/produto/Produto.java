package negocio.entidade.produto;

import negocio.entidade.categoria.Categoria;
import negocio.excecoes.DadosInvalidosException;
import negocio.excecoes.EstoqueInsuficienteException;

import java.util.Objects;

/**
 * Representa a estrutura base para qualquer produto gerenciável no estoque.
 * Define os atributos e comportamentos comuns a todos os tipos de produtos.
 * @author Thayson Guedes
 */
public abstract class Produto {
    private int id;
    private String nome;
    private String descricao;
    private double precoVenda;
    private int estoqueMinimo;
    private int quantidadeDisponivel;
    private boolean statusAtivo;
    private Categoria categoria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws DadosInvalidosException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new DadosInvalidosException("O nome do produto não pode ser nulo ou vazio.");
        }
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) throws DadosInvalidosException {
        if (precoVenda < 0) {
            throw new DadosInvalidosException("O preço de venda não pode ser negativo.");
        }
        this.precoVenda = precoVenda;
    }

    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(int estoqueMinimo) {
        if (estoqueMinimo < 0) {
            throw new IllegalArgumentException("O estoque mínimo não pode ser negativo.");
        }
        this.estoqueMinimo = estoqueMinimo;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        if (quantidadeDisponivel < 0) {
            throw new IllegalArgumentException("A quantidade mínima disponível não pode ser negativa.");
        }
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public boolean isStatusAtivo() {
        return statusAtivo;
    }

    public void setStatusAtivo(boolean statusAtivo) {
        this.statusAtivo = statusAtivo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", precoVenda=" + precoVenda +
                ", estoqueMinimo=" + estoqueMinimo +
                ", quantidadeDisponivel=" + quantidadeDisponivel +
                ", statusAtivo=" + statusAtivo +
                ", categoria=" + categoria +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return id == produto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public abstract String exibirInformacoesDetalhadas();

    public Produto(int id, String nome, String descricao, double precoVenda, int estoqueMinimo, int quantidadeDisponivel, boolean statusAtivo, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.precoVenda = precoVenda;
        this.estoqueMinimo = estoqueMinimo;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.statusAtivo = statusAtivo;
        this.categoria = categoria;
    }

    public void registrarEntrada(int quantidade) {
        if (quantidade >= 1) {
            this.quantidadeDisponivel += quantidade;
        }
    }

    public void registrarSaida(int quantidade) throws EstoqueInsuficienteException, DadosInvalidosException {
        if (quantidade <= 0) {
            throw new DadosInvalidosException("A quantidade de saída deve ser positiva.");

        } else if (this.getQuantidadeDisponivel() < quantidade) {
            throw new EstoqueInsuficienteException("Estoque insuficiente para o produto: " + this.getNome());

        } else {
            this.quantidadeDisponivel -= quantidade;
        }
    }

    public boolean verificarEstoqueBaixo() {
        return this.quantidadeDisponivel < this.estoqueMinimo;
    }

    public void consultarProduto() {

    }

    public void editarProduto() {

    }

    public void removerProduto() {

    }
}
