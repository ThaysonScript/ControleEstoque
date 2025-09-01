package dados;

import fachada.Fachada;
import negocio.entidade.*;
import negocio.excecoes.NegocioException;

import java.time.LocalDate;
import java.util.Arrays;

public class DadosPreDefinidos {
    public static void carregar() {
        System.out.println(">>> Carregando dados iniciais no sistema...");
        Fachada fachada = Fachada.getInstance();

        try {
            fachada.cadastrarCategoria(new Categoria("Eletrônicos", "Dispositivos e acessórios."));
            fachada.cadastrarCategoria(new Categoria("Alimentos", "Produtos alimentícios."));
            fachada.cadastrarCategoria(new Categoria("Ferramentas", "Ferramentas manuais e elétricas."));

            Categoria catEletronicos = fachada.buscarCategoriaPorNome("Eletrônicos");
            Categoria catAlimentos = fachada.buscarCategoriaPorNome("Alimentos");
            Categoria catFerramentas = fachada.buscarCategoriaPorNome("Ferramentas");

            ProdutoComNumeroSerie p1 = new ProdutoComNumeroSerie(0, "Notebook Pro", "Notebook de alta performance", 9500.00, 5, 0, true, catEletronicos, 24);
            fachada.cadastrarProduto(p1);
            p1.registrarEntrada(Arrays.asList("SN-NP1001", "SN-NP1002", "SN-NP1003", "SN-NP1004")); // Estoque = 4

            ProdutoPerecivel p2 = new ProdutoPerecivel(0, "Queijo Minas", "Queijo fresco", 25.00, 10, 0, true, catAlimentos, "QT2025C", LocalDate.now().plusDays(12));
            fachada.cadastrarProduto(p2);
            p2.registrarEntrada(30);

            ProdutoFracionado p3 = new ProdutoFracionado(0, "Fio de Cobre 2.5mm", "Fio Durável", 20000, 0, 3, true, catFerramentas, 1000,"Kg");
            fachada.cadastrarProduto(p3);
            p3.registrarEntrada(15.5);

            // 4. Produto Padrão (usando classe anônima)
            Produto p4 = new Produto(0, "Mouse sem Fio", "Mouse óptico", 120.00, 10, 5, true, catEletronicos) {
                @Override
                public String exibirInformacoesDetalhadas() {
                    return this.toString() + " | Tipo: Padrão";
                }
            };
            fachada.cadastrarProduto(p4); // Estoque = 5, abaixo do mínimo

            // 5. Produto Composto (Kit)
            Produto teclado = new Produto(0, "Teclado Mecânico", "Switch azul", 350.00, 8, 20, true, catEletronicos) {
                @Override public String exibirInformacoesDetalhadas() { return this.toString() + " | Tipo: Padrão"; }
            };
//            fachada.cadastrarProduto(teclado);
//            teclado = fachada.buscarProdutoPorNome("Teclado Mecânico");

//            ProdutoComposto p5 = new ProdutoComposto(0, "Kit Gamer", 450.00, "Kit com teclado e mouse", 5, 0, true, catEletronicos, new HashMap<>());
//            p5.adicionarComponente(p4, 1);
//            p5.adicionarComponente(teclado, 1);
//            fachada.cadastrarProduto(p5);

            System.out.println(">>> Dados carregados. Sistema pronto para uso. <<<");

        } catch (NegocioException e) {
            System.err.println("!!! Erro crítico ao carregar dados iniciais: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
