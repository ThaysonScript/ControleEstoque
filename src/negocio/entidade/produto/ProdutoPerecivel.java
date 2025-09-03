package negocio.entidade.produto;

import negocio.entidade.categoria.Categoria;
import negocio.excecoes.DadosInvalidosException;

import java.time.LocalDate;

/**
 * Representa um produto com data de validade e lote.
 * AJUSTADO: Adicionado construtor completo e validações.
 * @author Thayson Guedes
 */
public class ProdutoPerecivel extends Produto {
    private String lote;
    private LocalDate dataValidade;

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    @Override
    public String exibirInformacoesDetalhadas() {
        return super.toString() + " | Lote: " + this.lote + " | Validade: " + this.dataValidade;
    }

    public ProdutoPerecivel(int id, String nome, String descricao, double precoVenda, int estoqueMinimo, int quantidadeDisponivel, boolean statusAtivo, Categoria categoria, String lote, LocalDate dataValidade) throws DadosInvalidosException {
        super(id, nome, descricao, precoVenda, estoqueMinimo, quantidadeDisponivel, statusAtivo, categoria);
        this.lote = lote;

        if (dataValidade == null || dataValidade.isBefore(LocalDate.now())) {
            throw new DadosInvalidosException("A data de validade não pode ser nula ou retroativa.");

        } else {
            this.dataValidade = dataValidade;
        }
    }

    public boolean estaVencido() {
        return LocalDate.now().isAfter(this.dataValidade);
    }
}