package negocio.entidade.produto;

import negocio.entidade.categoria.Categoria;

/**
 * Representa um produto vendido de forma fracionada (e.g., por quilo, litro, metro).
 * AJUSTADO: A lógica de quantidade foi refeita para ser compatível com a classe pai.
 * @author Thayson Guedes
 */
public class ProdutoFracionado extends Produto {
    private String unidadeDeMedida;
    private final int fatorConversao;

    public String getUnidadeDeMedida() {
        return unidadeDeMedida;
    }

    public void setUnidadeDeMedida(String unidadeDeMedida) {
        this.unidadeDeMedida = unidadeDeMedida;
    }

    public int getFatorConversao() {
        return fatorConversao;
    }

    @Override
    public String exibirInformacoesDetalhadas() {
        String quantidadeFormatada = String.format("%.3f", getQuantidadeDisponivelFracionada());
        return "ID: " + getId() + " | Produto: " + getNome() + " | Qtd: " + quantidadeFormatada + " " + this.unidadeDeMedida;
    }

    public ProdutoFracionado(int id, String nome, String descricao, double precoVenda, int estoqueMinimo, int quantidadeDisponivel, boolean statusAtivo, Categoria categoria, int fatorConversao, String unidadeDeMedida) {
        super(id, nome, descricao, precoVenda, estoqueMinimo, quantidadeDisponivel, statusAtivo, categoria);
        this.fatorConversao = fatorConversao;
        this.unidadeDeMedida = unidadeDeMedida;
    }

    public double getQuantidadeDisponivelFracionada() {
        return (double) super.getQuantidadeDisponivel() / this.fatorConversao;
    }

    public double getEstoqueMinimoFracionado() {
        return (double) super.getEstoqueMinimo() / this.fatorConversao;
    }

    public void registrarEntrada(double quantidadeFracionada) {
        int quantidadeEmUnidadeBase = (int) (quantidadeFracionada * this.fatorConversao);
        super.registrarEntrada(quantidadeEmUnidadeBase);
    }

    public void registrarSaida(double quantidadeFracionada) throws Exception {
        int quantidadeEmUnidadeBase = (int) (quantidadeFracionada * this.fatorConversao);
        super.registrarSaida(quantidadeEmUnidadeBase);
    }
}