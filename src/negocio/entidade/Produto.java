package negocio.entidade;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class Produto {
    private final UUID id;
    private String nome;
    private String descricao;
    private float precoCusto;
    private float precoVenda;
    private int estoqueAtual;
    private boolean ativo;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataUltimaAtualizacao;
    private Categoria categoria;
    private Fornecedor fornecedor;

    
    public LocalDateTime getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    public void setDataUltimaAtualizacao() {
        this.dataUltimaAtualizacao = LocalDateTime.now();
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao() {
        this.dataCriacao = LocalDateTime.now();
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public int getEstoqueAtual() {
        return estoqueAtual;
    }

    public void setEstoqueAtual(int estoqueAtual) {
        this.estoqueAtual = estoqueAtual;
    }

    public float getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(float precoVenda) {
        this.precoVenda = precoVenda;
    }

    public float getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(float precoCusto) {
        this.precoCusto = precoCusto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    protected Produto(UUID id) {
        this.id = id;
    }

    public void criarProduto(
        String nome, String descricao,
        float precoCusto, float precoVenda,
        int estoqueAtual, boolean ativo,
        Categoria categoria, Fornecedor fornecedor
    ) {
        setNome(nome);
        setDescricao(descricao);
        setPrecoCusto(precoCusto);
        setPrecoVenda(precoVenda);
        setEstoqueAtual(estoqueAtual);
        setAtivo(ativo);
        setDataCriacao();
        setDataUltimaAtualizacao();

        this.categoria = categoria;
        this.fornecedor = fornecedor;
    }

    public void consultarProduto() {

    }

    public void editarProduto() {

    }

    public void removerProduto() {

    }
}
