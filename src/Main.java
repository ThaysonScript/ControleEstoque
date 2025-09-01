package src;

import apresentacao.TelaCategorias;
import apresentacao.TelaProdutos;
import apresentacao.TelaRelatorios;
import dados.DadosPreDefinidos;
import fachada.Fachada;

import java.util.Scanner;

public class Main {
    private static Fachada fachada = Fachada.getInstance();
    private static Scanner scanner = new Scanner(System.in);
    private static TelaCategorias telaCategorias = new TelaCategorias(fachada, scanner);
    private static TelaProdutos telaProdutos = new TelaProdutos(fachada, scanner);
    private static TelaRelatorios telaRelatorios = new TelaRelatorios(fachada);

    public static void main(String[] args) throws Exception {
        DadosPreDefinidos.carregar();

        int opcao = -1;
        while (opcao != 0) {
            exibirMenuPrincipal();
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1: telaProdutos.exibirMenu(); break;
                    case 2: telaCategorias.exibirMenu(); break;
                    case 3: telaRelatorios.exibirMenu(); break;
                    case 0: System.out.println("\nSaindo do sistema..."); break;
                    default: System.out.println("\nOpção inválida. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nErro: Por favor, digite um número válido.");
                opcao = -1;
            }
            System.out.println("\nPressione Enter para continuar...");
            scanner.nextLine();
        }
        scanner.close();
    }

    private static void exibirMenuPrincipal() {
        System.out.println("========= CONTROLE DE ESTOQUE =========");
        System.out.println("1. Gerenciar Produtos");
        System.out.println("2. Gerenciar Categorias");
        System.out.println("3. Gerar Relatórios");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }
}
