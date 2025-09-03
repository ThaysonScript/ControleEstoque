package infra.seeds;

import fachada.CategoriaFachada;
import fachada.ProdutoFachada;
import negocio.entidade.categoria.Categoria;
import negocio.entidade.produto.*;
import negocio.excecoes.NegocioException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;

public class DadosPreDefinidos {
    public static void carregar() {
        System.out.println(">>> Carregando dados iniciais no sistema...");
        ProdutoFachada produtoFachada = ProdutoFachada.getInstance();
        CategoriaFachada categoriaFachada = CategoriaFachada.getInstance();

        try {
            // Categorias
            categoriaFachada.cadastrarCategoria(new Categoria("Eletrônicos", "Dispositivos e acessórios."));
            categoriaFachada.cadastrarCategoria(new Categoria("Alimentos", "Produtos alimentícios."));
            categoriaFachada.cadastrarCategoria(new Categoria("Ferramentas", "Ferramentas manuais e elétricas."));

            Categoria catEletronicos = categoriaFachada.buscarCategoriaPorNome("Eletrônicos");
            Categoria catAlimentos   = categoriaFachada.buscarCategoriaPorNome("Alimentos");
            Categoria catFerramentas = categoriaFachada.buscarCategoriaPorNome("Ferramentas");

            // === Produtos com Número de Série (3) ===
            ProdutoComNumeroSerie p1 = new ProdutoComNumeroSerie(0, "Notebook Pro", "Notebook de alta performance",
                    9500.00, 5, 0, true, catEletronicos, 24);
            produtoFachada.cadastrarProduto(p1);
            p1.registrarEntrada(Arrays.asList("SN-NP1001", "SN-NP1002", "SN-NP1003"));

            ProdutoComNumeroSerie p2 = new ProdutoComNumeroSerie(0, "Smartphone X", "Modelo topo de linha",
                    4200.00, 10, 0, true, catEletronicos, 12);
            produtoFachada.cadastrarProduto(p2);
            p2.registrarEntrada(Arrays.asList("SN-SX001", "SN-SX002", "SN-SX003", "SN-SX004"));

            ProdutoComNumeroSerie p3 = new ProdutoComNumeroSerie(0, "Console Z", "Videogame última geração",
                    3500.00, 5, 0, true, catEletronicos, 24);
            produtoFachada.cadastrarProduto(p3);
            p3.registrarEntrada(Arrays.asList("SN-CZ001", "SN-CZ002"));

            // === Produtos Perecíveis (3) ===
            ProdutoPerecivel a1 = new ProdutoPerecivel(0, "Queijo Minas", "Queijo fresco",
                    25.00, 10, 0, true, catAlimentos, "QT2025C", LocalDate.now().plusDays(12));
            produtoFachada.cadastrarProduto(a1);
            a1.registrarEntrada(30);

            ProdutoPerecivel a2 = new ProdutoPerecivel(0, "Iogurte Natural", "Integral 170g",
                    3.50, 20, 0, true, catAlimentos, "L123", LocalDate.now().plusDays(8));
            produtoFachada.cadastrarProduto(a2);
            a2.registrarEntrada(100);

            ProdutoPerecivel a3 = new ProdutoPerecivel(0, "Pão de Forma", "500g",
                    7.50, 15, 0, true, catAlimentos, "P456", LocalDate.now().plusDays(5));
            produtoFachada.cadastrarProduto(a3);
            a3.registrarEntrada(40);

            // === Produtos Fracionados (3) ===
            ProdutoFracionado f1 = new ProdutoFracionado(0, "Fio de Cobre 2.5mm", "Fio durável",
                    20000, 0, 0, true, catFerramentas, 1000, "Kg");
            produtoFachada.cadastrarProduto(f1);
            f1.registrarEntrada(15.5);

            ProdutoFracionado f2 = new ProdutoFracionado(0, "Tinta Acrílica", "Baldes de 18L",
                    450.00, 5, 0, true, catFerramentas, 1, "Balde");
            produtoFachada.cadastrarProduto(f2);
            f2.registrarEntrada(12);

            ProdutoFracionado f3 = new ProdutoFracionado(0, "Cabo de Rede Cat6", "Caixa 305m",
                    350.00, 2, 0, true, catFerramentas, 305, "Metro");
            produtoFachada.cadastrarProduto(f3);
            f3.registrarEntrada(610); // 2 caixas = 610m

            // === Produtos Padrão (3) ===
            Produto pad1 = new Produto(0, "Mouse sem Fio", "Mouse óptico",
                    120.00, 10, 5, true, catEletronicos) {
                @Override public String exibirInformacoesDetalhadas() { return this.toString() + " | Tipo: Padrão"; }
            };
            produtoFachada.cadastrarProduto(pad1);

            Produto pad2 = new Produto(0, "Teclado Mecânico", "Switch azul",
                    350.00, 8, 20, true, catEletronicos) {
                @Override public String exibirInformacoesDetalhadas() { return this.toString() + " | Tipo: Padrão"; }
            };
            produtoFachada.cadastrarProduto(pad2);

            Produto pad3 = new Produto(0, "Monitor 24\"", "Full HD",
                    850.00, 5, 3, true, catEletronicos) {
                @Override public String exibirInformacoesDetalhadas() { return this.toString() + " | Tipo: Padrão"; }
            };
            produtoFachada.cadastrarProduto(pad3);

            // === Produtos Compostos (3 kits) ===
            ProdutoComposto kit1 = new ProdutoComposto(0, "Kit Gamer Básico", "Mouse + Teclado",
                    450.00, 5, 0, true, catEletronicos, new HashMap<>());
            kit1.adicionarComponente(pad1, 1);
            kit1.adicionarComponente(pad2, 1);
            produtoFachada.cadastrarProduto(kit1);

            ProdutoComposto kit2 = new ProdutoComposto(0, "Kit Trabalho", "Teclado + Monitor",
                    1100.00, 3, 0, true, catEletronicos, new HashMap<>());
            kit2.adicionarComponente(pad2, 1);
            kit2.adicionarComponente(pad3, 1);
            produtoFachada.cadastrarProduto(kit2);

            ProdutoComposto kit3 = new ProdutoComposto(0, "Kit Estudante", "Notebook + Mouse",
                    9600.00, 2, 0, true, catEletronicos, new HashMap<>());
            kit3.adicionarComponente(p1, 1);   // Notebook Pro
            kit3.adicionarComponente(pad1, 1); // Mouse sem fio
            produtoFachada.cadastrarProduto(kit3);

            System.out.println(">>> Dados carregados. Sistema pronto para uso. <<<");

        } catch (NegocioException e) {
            System.err.println("!!! Erro crítico ao carregar dados iniciais: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
