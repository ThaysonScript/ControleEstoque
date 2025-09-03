package apresentacao;

import fachada.RelatorioFachada;
import negocio.servicos.relatorios.Relatorio;

import java.util.Scanner;

public class TelaRelatorios {
    private final RelatorioFachada relatorioFachada;
    private final Scanner scanner;

    public TelaRelatorios(RelatorioFachada relatorioFachada, Scanner scanner) {
        this.relatorioFachada = relatorioFachada;
        this.scanner = scanner;
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Gerar Relatórios ---");
            System.out.println("1. Relatório de Estoque Baixo");
            System.out.println("2. Relatório de Produtos a Vencer");
            System.out.println("0. Voltar ao menu principal");
            System.out.println("------------------------\n");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                Relatorio relatorio = null;

                switch (opcao) {
                    case 1:
                        System.out.print("Digite o estoque máximo para filtrar (deixe em branco para usar o padrão de cada produto): ");
                        String limiteStr = scanner.nextLine();

                        if (limiteStr.trim().isEmpty()) {
                            relatorio = relatorioFachada.gerarRelatorioEstoqueBaixo();
                        } else {
                            int limite = Integer.parseInt(limiteStr);
                            relatorio = relatorioFachada.gerarRelatorioEstoqueAbaixoDe(limite);
                        }
                        break;
                    case 2:
                        relatorio = relatorioFachada.gerarRelatorioProdutosAVencer();
                        break;
                    case 0:
                        continue;
                    default:
                        System.out.println("Opção inválida.");
                }

                if (relatorio != null) {
                    relatorio.exibir();
                }

            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada inválida. Digite um número.");
            } catch (Exception e) {
                System.err.println("Erro inesperado ao gerar relatório: " + e.getMessage());
            }
        }
    }
}