package fachada;

import negocio.repositorio.IRepositorioProdutos;
import negocio.servicos.relatorios.GeradorRelatorioEstoqueBaixo;
import negocio.servicos.relatorios.GeradorRelatorioEstoqueFiltrado;
import negocio.servicos.relatorios.GeradorRelatorioProdutosAVencer;
import negocio.servicos.relatorios.Relatorio;

public class RelatorioFachada {
    private static RelatorioFachada instance;
    private final IRepositorioProdutos repositorioProdutos;

    private RelatorioFachada(IRepositorioProdutos repositorioProdutos) {
        this.repositorioProdutos = repositorioProdutos;
    }

    public static void init(IRepositorioProdutos repositorioProdutos) {
        if (instance == null) {
            instance = new RelatorioFachada(repositorioProdutos);
        }
    }

    public static RelatorioFachada getInstance() {
        return instance;
    }

    public Relatorio gerarRelatorioEstoqueBaixo() {
        GeradorRelatorioEstoqueBaixo gerador = new GeradorRelatorioEstoqueBaixo(this.repositorioProdutos);
        return gerador.gerar();
    }

    public Relatorio gerarRelatorioProdutosAVencer() {
        GeradorRelatorioProdutosAVencer gerador = new GeradorRelatorioProdutosAVencer(this.repositorioProdutos);
        return gerador.gerar();
    }

    public Relatorio gerarRelatorioEstoqueAbaixoDe(int limite) {
        GeradorRelatorioEstoqueFiltrado gerador = new GeradorRelatorioEstoqueFiltrado(this.repositorioProdutos, limite);
        return gerador.gerar();
    }
}
