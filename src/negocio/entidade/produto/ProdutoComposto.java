package negocio.entidade.produto;

import negocio.entidade.categoria.Categoria;
import negocio.excecoes.DadosInvalidosException;
import negocio.excecoes.EstoqueInsuficienteException;

import java.util.Map;

/**
 * Representa um produto composto, como um "Kit".
 * AJUSTADO: Construtor e propagação de exceções.
 * @author Thayson Guedes
 */
public class ProdutoComposto extends Produto {
    private final Map<Produto, Integer> componentes;

    public Map<Produto, Integer> getComponentes() {
        return componentes;
    }

    @Override
    public int getQuantidadeDisponivel() {
        if (componentes.isEmpty()) return 0;
        int maxKitsPossiveis = Integer.MAX_VALUE;
        for (Map.Entry<Produto, Integer> entry : componentes.entrySet()) {
            int kitsPossiveisComEsteComponente = entry.getKey().getQuantidadeDisponivel() / entry.getValue();
            if (kitsPossiveisComEsteComponente < maxKitsPossiveis) {
                maxKitsPossiveis = kitsPossiveisComEsteComponente;
            }
        }
        return maxKitsPossiveis;
    }

    @Override
    public void registrarSaida(int quantidadeKits) throws EstoqueInsuficienteException, DadosInvalidosException {
        if (getQuantidadeDisponivel() < quantidadeKits) {
            throw new EstoqueInsuficienteException("Estoque de componentes insuficiente para montar " + quantidadeKits + " kits.");
        }

        try {
            if (getQuantidadeDisponivel() >= quantidadeKits) {
                for (Map.Entry<Produto, Integer> entry : componentes.entrySet()) {
                    Produto componente = entry.getKey();
                    int quantidadeDebitar = entry.getValue() * quantidadeKits;
                    componente.registrarSaida(quantidadeDebitar);
                }
            }
        } catch (EstoqueInsuficienteException e) {
            throw new EstoqueInsuficienteException("Falha ao dar baixa no componente '" + e.getMessage());
        }
    }

    @Override
    public String exibirInformacoesDetalhadas() {
        StringBuilder detalhes = new StringBuilder();
        detalhes.append(super.toString().replace("Qtd", "Qtd Montável"));
        detalhes.append("\n  Componentes:");
        for (Map.Entry<Produto, Integer> entry : componentes.entrySet()) {
            detalhes.append("\n  - ").append(entry.getKey().getNome()).append(" (x").append(entry.getValue()).append(")");
        }
        return detalhes.toString();
    }

    public ProdutoComposto(int id, String nome, String descricao, double precoVenda, int estoqueMinimo, int quantidadeDisponivel, boolean statusAtivo, Categoria categoria, Map<Produto, Integer> componentes) {
        super(id, nome, descricao, precoVenda, estoqueMinimo, quantidadeDisponivel, statusAtivo, categoria);
        this.componentes = componentes;
    }

    public void adicionarComponente(Produto produto, int quantidadeNecessaria) {
        this.componentes.put(produto, quantidadeNecessaria);
    }
}