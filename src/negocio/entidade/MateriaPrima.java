package negocio.entidade;

import java.time.LocalDateTime;

public class MateriaPrima extends Produto {
    private String localizacaoArmazem;

    public String getLocalizacaoArmazem() {
        return localizacaoArmazem;
    }

    public void setLocalizacaoArmazem(String localizacaoArmazem) {
        this.localizacaoArmazem = localizacaoArmazem;
    }

    public MateriaPrima(String nome, String descricao, float precoCusto, float precoVenda, int estoqueAtual, boolean ativo, LocalDateTime dataCriacao, LocalDateTime dataUltimaAtualizacao) {
        super(nome, descricao, precoCusto, precoVenda, estoqueAtual, ativo, dataCriacao, dataUltimaAtualizacao);
    }
}
