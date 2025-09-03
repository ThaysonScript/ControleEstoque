package negocio.servicos.relatorios;

import negocio.entidade.produto.Produto;
import negocio.repositorio.IRepositorioProdutos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GeradorRelatorioEstoqueBaixo implements IGeradorRelatorio {
    private final IRepositorioProdutos repositorioProdutos;

    public GeradorRelatorioEstoqueBaixo(IRepositorioProdutos repositorioProdutos) {
        this.repositorioProdutos = repositorioProdutos;
    }

    @Override
    public Relatorio gerar() {
        String titulo = "Relatório de Produtos com Estoque Baixo";
        List<String> cabecalho = Arrays.asList("ID Produto", "Nome", "Qtd. Atual", "Qtd. Mínima");

        List<List<String>> linhas = new ArrayList<>();
        List<Produto> produtosComEstoqueBaixo = this.repositorioProdutos.listarEstoqueBaixo();

        for (Produto produto : produtosComEstoqueBaixo) {
            List<String> linha = Arrays.asList(
                    String.valueOf(produto.getId()),
                    produto.getNome(),
                    String.valueOf(produto.getQuantidadeDisponivel()),
                    String.valueOf(produto.getEstoqueMinimo())
            );
            linhas.add(linha);
        }

        String sumario = "Total de " + produtosComEstoqueBaixo.size() + " produtos com estoque abaixo do mínimo.";

        return new Relatorio(titulo, cabecalho, linhas, sumario);
    }
}