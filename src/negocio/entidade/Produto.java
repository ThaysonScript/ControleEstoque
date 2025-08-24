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
    private final LocalDateTime dataCriacao;
    private LocalDateTime dataUltimaAtualizacao;

    private final Categoria categoria;
    private final Fornecedor fornecedor;
    
    public LocalDateTime getDataUltimaAtualizacao() {
        return dataUltimaAtualizacao;
    }

    public void setDataUltimaAtualizacao(LocalDateTime dataUltimaAtualizacao) {
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
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

    protected Produto(UUID id, String nome, String descricao, float precoCusto, float precoVenda, int estoqueAtual, boolean ativo, LocalDateTime dataCriacao, LocalDateTime dataUltimaAtualizacao, Categoria categoria, Fornecedor fornecedor) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.estoqueAtual = estoqueAtual;
        this.ativo = ativo;
        this.dataCriacao = dataCriacao;
        this.dataUltimaAtualizacao = dataUltimaAtualizacao;
        this.categoria = categoria;
        this.fornecedor = fornecedor;
    }
}
