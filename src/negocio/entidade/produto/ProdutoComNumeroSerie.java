package negocio.entidade.produto;

import negocio.entidade.categoria.Categoria;
import negocio.excecoes.ItemNaoEncontradoException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Representa um produto cujo controle de estoque é feito por números de série individuais.
 * Ideal para eletrônicos, equipamentos ou itens de alto valor.
 * @author Thayson Guedes
 */
public class ProdutoComNumeroSerie extends Produto {
    private int garantiaMeses;
    private List<String> numerosDeSerieDisponiveis;

    public int getGarantiaMeses() {
        return garantiaMeses;
    }

    public void setGarantiaMeses(int garantiaMeses) {
        this.garantiaMeses = garantiaMeses;
    }

    public List<String> getNumerosDeSerieDisponiveis() {
        return numerosDeSerieDisponiveis;
    }

    public void setNumerosDeSerieDisponiveis(List<String> numerosDeSerieDisponiveis) {
        this.numerosDeSerieDisponiveis = numerosDeSerieDisponiveis;
    }

    @Override
    public int getQuantidadeDisponivel() {
        return this.numerosDeSerieDisponiveis.size();
    }

    @Override
    public String exibirInformacoesDetalhadas() {
        return super.toString() + " | Garantia: " + this.garantiaMeses + " meses";
    }

    public void registrarEntrada(List<String> novosNumerosDeSerie) {
        Set<String> tempSet = new HashSet<>(novosNumerosDeSerie);
        if (tempSet.size() != novosNumerosDeSerie.size()) {
            throw new IllegalArgumentException("A lista de entrada contém números de série duplicados.");
        }
        this.numerosDeSerieDisponiveis.addAll(novosNumerosDeSerie);
    }

    public void registrarSaida(String numeroDeSerie) throws ItemNaoEncontradoException {
        boolean removido = this.numerosDeSerieDisponiveis.remove(numeroDeSerie);
        if (!removido) {
            throw new ItemNaoEncontradoException("Número de série '" + numeroDeSerie + "' não encontrado no estoque.");
        }
    }

    public ProdutoComNumeroSerie(int id, String nome, String descricao, double precoVenda, int estoqueMinimo, int quantidadeDisponivel, boolean statusAtivo, Categoria categoria, int garantiaMeses) {
        super(id, nome, descricao, precoVenda, estoqueMinimo, quantidadeDisponivel, statusAtivo, categoria);
        this.garantiaMeses = garantiaMeses;
        this.numerosDeSerieDisponiveis = new ArrayList<>();
    }

}