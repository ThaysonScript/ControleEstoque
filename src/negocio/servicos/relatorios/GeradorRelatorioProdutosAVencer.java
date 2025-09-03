package negocio.servicos.relatorios;

import negocio.entidade.produto.Produto;
import negocio.entidade.produto.ProdutoPerecivel;
import negocio.repositorio.IRepositorioProdutos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeradorRelatorioProdutosAVencer implements IGeradorRelatorio {

    private final IRepositorioProdutos repositorioProdutos;
    private static final int DIAS_ANTECEDENCIA_VENCIMENTO = 15;

    public GeradorRelatorioProdutosAVencer(IRepositorioProdutos repositorioProdutos) {
        this.repositorioProdutos = repositorioProdutos;
    }

    @Override
    public Relatorio gerar() {
        String titulo = "Relatório de Produtos Próximos do Vencimento";
        List<String> cabecalho = Arrays.asList("ID Produto", "Nome", "Lote", "Data de Validade", "Dias Restantes");

        List<List<String>> linhas = new ArrayList<>();
        List<Produto> todosOsProdutos = this.repositorioProdutos.listarTodos();
        LocalDate hoje = LocalDate.now();

        for (Produto produto : todosOsProdutos) {
            if (produto instanceof ProdutoPerecivel) {
                ProdutoPerecivel perecivel = (ProdutoPerecivel) produto;
                long diasParaVencer = ChronoUnit.DAYS.between(hoje, perecivel.getDataValidade());

                if (diasParaVencer >= 0 && diasParaVencer <= DIAS_ANTECEDENCIA_VENCIMENTO) {
                    List<String> linha = Arrays.asList(
                            String.valueOf(perecivel.getId()),
                            perecivel.getNome(),
                            perecivel.getLote(),
                            perecivel.getDataValidade().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                            String.valueOf(diasParaVencer)
                    );
                    linhas.add(linha);
                }
            }
        }

        String sumario = "Total de " + linhas.size() + " produtos vencendo nos próximos " + DIAS_ANTECEDENCIA_VENCIMENTO + " dias.";
        return new Relatorio(titulo, cabecalho, linhas, sumario);
    }
}