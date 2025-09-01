package apresentacao;

import fachada.Fachada;
import negocio.entidade.Relatorio;

public class TelaRelatorios {
    private Fachada fachada;

    public TelaRelatorios(Fachada fachada) {
        this.fachada = fachada;
    }

    public void exibirMenu() {
        System.out.println("\n--- Gerar Relatórios ---");
        System.out.println("Gerando Relatório de Estoque Baixo...");

        Relatorio relatorio = fachada.gerarRelatorioEstoqueBaixo();
        relatorio.exibir();
    }
}