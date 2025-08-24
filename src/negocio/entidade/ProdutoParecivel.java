package negocio.entidade;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProdutoParecivel extends Produto {
    private LocalDate dataValidade;
    private String lote;
    private String instrucoesArmazenamento;

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getInstrucoesArmazenamento() {
        return instrucoesArmazenamento;
    }

    public void setInstrucoesArmazenamento(String instrucoesArmazenamento) {
        this.instrucoesArmazenamento = instrucoesArmazenamento;
    }

    public ProdutoParecivel(String nome, String descricao, float precoCusto, float precoVenda, int estoqueAtual, boolean ativo, LocalDateTime dataCriacao, LocalDateTime dataUltimaAtualizacao) {
        super(nome, descricao, precoCusto, precoVenda, estoqueAtual, ativo, dataCriacao, dataUltimaAtualizacao);
    }
}
