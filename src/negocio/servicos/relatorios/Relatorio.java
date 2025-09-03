package negocio.servicos.relatorios;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Relatorio {
    private final String titulo;
    private final LocalDateTime dataGeracao;
    private final List<String> cabecalhoColunas;
    private final List<List<String>> linhas;
    private final String sumario;

    public Relatorio(String titulo, List<String> cabecalhoColunas, List<List<String>> linhas, String sumario) {
        this.titulo = titulo;
        this.dataGeracao = LocalDateTime.now();
        this.cabecalhoColunas = cabecalhoColunas;
        this.linhas = linhas;
        this.sumario = sumario;
    }

    public void exibir() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println("========================================================");
        System.out.println(this.titulo);
        System.out.println("Gerado em: " + this.dataGeracao.format(formatter));
        System.out.println("--------------------------------------------------------");

        for (String cabecalho : this.cabecalhoColunas) {
            System.out.printf("%-20s", cabecalho);
        }
        System.out.println();
        System.out.println("--------------------------------------------------------");

        for (List<String> linha : this.linhas) {
            for (String dado : linha) {
                System.out.printf("%-20s", dado);
            }
            System.out.println();
        }

        System.out.println("--------------------------------------------------------");
        System.out.println(this.sumario);
        System.out.println("========================================================");
    }
}