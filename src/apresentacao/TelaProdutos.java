package apresentacao;

import fachada.CategoriaFachada;
import fachada.ProdutoFachada;
import negocio.entidade.categoria.Categoria;
import negocio.entidade.produto.Produto;
import negocio.entidade.produto.ProdutoComNumeroSerie;
import negocio.entidade.produto.ProdutoPerecivel;
import negocio.excecoes.NegocioException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class TelaProdutos {
    private final ProdutoFachada produtoFachada;
    private final CategoriaFachada categoriaFachada;
    private final Scanner scanner;

    public TelaProdutos(ProdutoFachada produtoFachada, CategoriaFachada categoriaFachada, Scanner scanner) {
        this.produtoFachada = produtoFachada;
        this.categoriaFachada = categoriaFachada;
        this.scanner = scanner;
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- Gerenciar Produtos ---");
            System.out.println("1. Cadastrar novo produto");
            System.out.println("2. Listar todos os produtos");
            System.out.println("3. Buscar produto");
            System.out.println("4. Atualizar produto");
            System.out.println("5. Remover produto");
            System.out.println("0. Voltar ao menu principal");
            System.out.println("--------------------------\n\n");
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

            } catch (Exception e) {
                System.err.println("Erro inesperado: " + e.getMessage());
            }
        }
    }

    private void listar() {
        System.out.println("\n--- Lista de Produtos ---");
        List<Produto> produtos = produtoFachada.listarTodosProdutos();

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
        Categoria categoria = categoriaFachada.buscarCategoriaPorId(catId);

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

        produtoFachada.cadastrarProduto(novoProduto);
        System.out.println("Produto '" + nome + "' cadastrado com sucesso!");
    }

    private void buscar() {
        System.out.println("\n--- Buscar Produto ---");
        System.out.println("Buscar por:");
        System.out.println("1. ID");
        System.out.println("2. Nome");
        System.out.print("Opção: ");
        int opcao = Integer.parseInt(scanner.nextLine());

        Produto produto = null;
        if (opcao == 1) {
            System.out.print("Digite o ID do produto: ");
            int id = Integer.parseInt(scanner.nextLine());
            produto = produtoFachada.buscarProdutoPorId(id);
        } else if (opcao == 2) {
            System.out.print("Digite o nome do produto: ");
            String nome = scanner.nextLine();
            produto = produtoFachada.buscarProdutoPorNome(nome);
        } else {
            System.out.println("Opção inválida.");
            return;
        }

        if (produto != null) {
            System.out.println("\n--- Produto Encontrado ---");
            System.out.println(produto.exibirInformacoesDetalhadas());
        } else {
            System.out.println("\nProduto não encontrado.");
        }
    }

    private void atualizar() throws NegocioException {
        System.out.println("\n--- Atualizar Produto ---");
        System.out.print("Digite o ID do produto a ser atualizado: ");
        int id = Integer.parseInt(scanner.nextLine());

        Produto produto = produtoFachada.buscarProdutoPorId(id);
        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.println("Deixe o campo em branco para não alterar o valor atual.");

        System.out.print("Novo nome (" + produto.getNome() + "): ");
        String nome = scanner.nextLine();
        if (!nome.trim().isEmpty()) {
            produto.setNome(nome);
        }

        System.out.print("Nova descrição (" + produto.getDescricao() + "): ");
        String descricao = scanner.nextLine();
        if (!descricao.trim().isEmpty()) {
            produto.setDescricao(descricao);
        }

        System.out.print("Novo Preço de Venda (" + produto.getPrecoVenda() + "): ");
        String precoStr = scanner.nextLine();
        if (!precoStr.trim().isEmpty()) {
            produto.setPrecoVenda(Double.parseDouble(precoStr));
        }

        System.out.print("Novo Estoque Mínimo (" + produto.getEstoqueMinimo() + "): ");
        String estMinStr = scanner.nextLine();
        if (!estMinStr.trim().isEmpty()) {
            produto.setEstoqueMinimo(Integer.parseInt(estMinStr));
        }

        System.out.print("Nova Quantidade Disponível (" + produto.getQuantidadeDisponivel() + "): ");
        String novaQuantidadeDisp = scanner.nextLine();
        if (!novaQuantidadeDisp.trim().isEmpty()) {
            produto.setQuantidadeDisponivel(Integer.parseInt(novaQuantidadeDisp));
        }

        System.out.print("Continua ativo? (" + produto.isStatusAtivo() + "): ");
        String atividade = String.valueOf(scanner.nextBoolean());
        if (!atividade.trim().isEmpty()) {
            produto.setStatusAtivo(Boolean.parseBoolean(atividade));
        }

        produtoFachada.atualizarProduto(produto);
        System.out.println("Produto atualizado com sucesso!");
    }

    private void remover() throws NegocioException {
        System.out.println("\n--- Remover Produto ---");
        System.out.print("Digite o ID do produto a ser removido: ");
        int id = Integer.parseInt(scanner.nextLine());

        Produto produto = produtoFachada.buscarProdutoPorId(id);
        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.println("Você tem certeza que deseja remover o produto: " + produto.getNome() + "? (S/N)");
        String confirmacao = scanner.nextLine();

        if (confirmacao.equalsIgnoreCase("S")) {
            produtoFachada.removerProduto(id);
            System.out.println("Produto removido com sucesso!");
        } else {
            System.out.println("Operação cancelada.");
        }
    }

    private void listarCategoriasParaSelecao() {
        System.out.println("--- Categorias disponíveis ---");
        List<Categoria> categorias = categoriaFachada.listarTodasCategorias();

        for(Categoria c : categorias) {
            System.out.println("  ID: " + c.getId() + " - " + c.getNome());
        }
        System.out.println("----------------------------");
    }
}