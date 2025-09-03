package negocio.servicos.relatorios;

import negocio.entidade.produto.Produto;
import negocio.repositorio.IRepositorioProdutos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Gera um relatório de produtos cuja quantidade em estoque está abaixo de um limite específico,
 * informado pelo usuário no momento da geração.
 * @author Thayson Guedes
 */
public class GeradorRelatorioEstoqueFiltrado implements IGeradorRelatorio {

    private final IRepositorioProdutos repositorioProdutos;
    private final int limiteEstoque;

    public GeradorRelatorioEstoqueFiltrado(IRepositorioProdutos repositorioProdutos, int limiteEstoque) {
        this.repositorioProdutos = repositorioProdutos;
        this.limiteEstoque = limiteEstoque;
    }

    @Override
    public Relatorio gerar() {
        String titulo = "Relatório de Produtos com Estoque Abaixo de " + this.limiteEstoque;
        // O "Estoque Mínimo" do produto é irrelevante aqui, então não o exibimos.
        List<String> cabecalho = Arrays.asList("ID Produto", "Nome", "Qtd. Disponível");

        List<List<String>> linhas = new ArrayList<>();
        // Usaremos um novo método do repositório para buscar os produtos abaixo do limite.
        List<Produto> produtosFiltrados = this.repositorioProdutos.listarAbaixoDe(this.limiteEstoque);

        for (Produto produto : produtosFiltrados) {
            List<String> linha = Arrays.asList(
                    String.valueOf(produto.getId()),
                    produto.getNome(),
                    String.valueOf(produto.getQuantidadeDisponivel())
            );
            linhas.add(linha);
        }

        String sumario = "Total de " + produtosFiltrados.size() + " produtos com estoque abaixo de " + this.limiteEstoque + " unidades.";
        return new Relatorio(titulo, cabecalho, linhas, sumario);
    }
}