package apresentacao;

import fachada.Fachada;
import negocio.entidade.Categoria;
import negocio.excecoes.NegocioException;

import java.util.List;
import java.util.Scanner;

public class TelaCategorias {
    private Fachada fachada;
    private Scanner scanner;

    public TelaCategorias(Fachada fachada, Scanner scanner) {
        this.fachada = fachada;
        this.scanner = scanner;
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n\n--- Gerenciar Categorias ---");
            System.out.println("1. Cadastrar nova categoria");
            System.out.println("2. Listar todas as categorias");
            System.out.println("3. Buscar categoria");
            System.out.println("4. Atualizar categoria");
            System.out.println("5. Remover categoria");
            System.out.println("0. Voltar ao menu principal");
            System.out.println("----------------------------\n");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1: cadastrar(); break;
                    case 2: listar(); break;
                    case 3: buscar(); break;
                    case 4: atualizar(); break;
                    case 5: remover(); break;
                    case 0: break;
                    default: System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada inválida. Digite um número.");

            } catch (NegocioException e) {
                System.err.println("Erro de Negócio: " + e.getMessage());
            }
        }
    }

    private void cadastrar() throws NegocioException {
        System.out.println("\n--- Cadastro de Categoria ---");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        fachada.cadastrarCategoria(new Categoria(nome, descricao));
        System.out.println("Categoria cadastrada com sucesso!");
    }

    private void listar() {
        System.out.println("\n--- Lista de Categorias ---");
        List<Categoria> categorias = fachada.listarTodasCategorias();

        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada.");
        } else {
            for (Categoria c : categorias) {
                System.out.println(c.toString());
            }
        }
    }

    private void buscar() {
        System.out.println("\n--- Buscar Categoria ---");
        System.out.println("Buscar por:");
        System.out.println("1. ID");
        System.out.println("2. Nome");
        System.out.print("Opção: ");
        int opcao = Integer.parseInt(scanner.nextLine());

        Categoria categoria = null;
        if (opcao == 1) {
            System.out.print("Digite o ID da categoria: ");
            int id = Integer.parseInt(scanner.nextLine());
            categoria = fachada.buscarCategoriaPorId(id);
        } else if (opcao == 2) {
            System.out.print("Digite o nome da categoria: ");
            String nome = scanner.nextLine();
            categoria = fachada.buscarCategoriaPorNome(nome);
        } else {
            System.out.println("Opção inválida.");
            return;
        }

        if (categoria != null) {
            System.out.println("\n--- Categoria Encontrada ---");
            System.out.println(categoria.toString());
        } else {
            System.out.println("\nCategoria não encontrada.");
        }
    }

    private void atualizar() throws NegocioException {
        System.out.println("\n--- Atualizar Categoria ---");
        System.out.print("Digite o ID da categoria a ser atualizada: ");
        int id = Integer.parseInt(scanner.nextLine());

        Categoria categoria = fachada.buscarCategoriaPorId(id);
        if (categoria == null) {
            System.out.println("Categoria não encontrada.");
            return;
        }

        System.out.print("Novo nome (deixe em branco para não alterar): ");
        String nome = scanner.nextLine();

        System.out.print("Nova descrição (deixe em branco para não alterar): ");
        String descricao = scanner.nextLine();

        if (!nome.trim().isEmpty()) categoria.setNome(nome);
        if (!descricao.trim().isEmpty()) categoria.setDescricao(descricao);

        fachada.atualizarCategoria(categoria);
        System.out.println("Categoria atualizada com sucesso!");
    }

    private void remover() throws NegocioException {
        System.out.println("\n--- Remover Categoria ---");
        System.out.print("Digite o ID da categoria a ser removida: ");
        int id = Integer.parseInt(scanner.nextLine());

        // Adicionando uma camada de confirmação para segurança
        Categoria categoria = fachada.buscarCategoriaPorId(id);
        if (categoria == null) {
            System.out.println("Categoria não encontrada.");
            return;
        }

        System.out.println("Você tem certeza que deseja remover a categoria: " + categoria.getNome() + "? (S/N)");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("S")) {
            fachada.removerCategoria(id);
            System.out.println("Categoria removida com sucesso!");
        } else {
            System.out.println("Operação cancelada.");
        }
    }
}