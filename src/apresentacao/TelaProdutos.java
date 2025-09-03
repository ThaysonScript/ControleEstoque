package apresentacao;

import fachada.Fachada;
import negocio.entidade.Categoria;
import negocio.entidade.Produto;
import negocio.entidade.ProdutoComNumeroSerie;
import negocio.entidade.ProdutoPerecivel;
import negocio.excecoes.NegocioException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class TelaProdutos {
    private Fachada fachada;
    private Scanner scanner;

    public TelaProdutos(Fachada fachada, Scanner scanner) {
        this.fachada = fachada;
        this.scanner = scanner;
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Gerenciar Produtos ---");
            System.out.println("1. Cadastrar novo produto");
            System.out.println("2. Listar todos os produtos");
            System.out.println("0. Voltar ao menu principal");
            System.out.println("--------------------------\n\n");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
                switch (opcao) {
                    case 1: cadastrar(); break;
                    case 2: listar(); break;
                    case 0: break;
                    default: System.out.println("Opção inválida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada inválida. Digite um número.");

            } catch (NegocioException e) {
                System.err.println("Erro de Negócio: " + e.getMessage());

            } catch (Exception e) {
                System.err.println("Erro inesperado: " + e.getMessage());
            }
        }
    }

    private void listar() {
        System.out.println("\n--- Lista de Produtos ---");
        List<Produto> produtos = fachada.listarTodosProdutos();

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            for (Produto p : produtos) {
                System.out.println(p.exibirInformacoesDetalhadas());
            }
        }
    }

    private void cadastrar() throws NegocioException {
        System.out.println("\n--- Cadastro de Produto ---");
        System.out.println("Qual tipo de produto deseja cadastrar?");
        System.out.println("1. Produto Padrão");
        System.out.println("2. Produto Perecível");
        System.out.println("3. Produto com Número de Série");
        System.out.print("Tipo: ");
        int tipo = Integer.parseInt(scanner.nextLine());

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        System.out.print("Preço de Venda: ");
        double preco = Double.parseDouble(scanner.nextLine());

        System.out.print("Estoque Mínimo: ");
        int estMin = Integer.parseInt(scanner.nextLine());

        listarCategoriasParaSelecao();

        System.out.print("ID da Categoria: ");
        int catId = Integer.parseInt(scanner.nextLine());
        Categoria categoria = fachada.buscarCategoriaPorId(catId);

        if (categoria == null) {
            throw new NegocioException("Categoria com ID " + catId + " não encontrada.");
        }

        Produto novoProduto = null;
        switch (tipo) {
            case 1:
                novoProduto = new Produto(0, nome, descricao, preco, estMin, 0, true, categoria) {
                    @Override public String exibirInformacoesDetalhadas() { return this.toString() + " | Tipo: Padrão";}
                };
                break;
            case 2:
                System.out.print("Lote: ");
                String lote = scanner.nextLine();
                System.out.print("Data de Validade (dd/MM/yyyy): ");
                LocalDate validade = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                novoProduto = new ProdutoPerecivel(0, nome, descricao, preco, estMin, 0, true, categoria, lote, validade);
                break;
            case 3:
                System.out.print("Garantia (meses): ");
                int garantia = Integer.parseInt(scanner.nextLine());

                novoProduto = new ProdutoComNumeroSerie(0, nome, descricao, preco, estMin, 0, true, categoria, garantia);
                break;
            default:
                System.out.println("Tipo de produto inválido.");
                return;
        }

        fachada.cadastrarProduto(novoProduto);
        System.out.println("Produto '" + nome + "' cadastrado com sucesso!");
    }

    private void listarCategoriasParaSelecao() {
        System.out.println("--- Categorias disponíveis ---");
        List<Categoria> categorias = fachada.listarTodasCategorias();

        for(Categoria c : categorias) {
            System.out.println("  ID: " + c.getId() + " - " + c.getNome());
        }
        System.out.println("----------------------------");
    }
}