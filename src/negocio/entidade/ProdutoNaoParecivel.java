package negocio.entidade;

import java.time.LocalDateTime;

public class ProdutoNaoParecivel extends Produto {
    private String material;
    private int garantiaMeses;

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getGarantiaMeses() {
        return garantiaMeses;
    }

    public void setGarantiaMeses(int garantiaMeses) {
        this.garantiaMeses = garantiaMeses;
    }

    public ProdutoNaoParecivel(String nome, String descricao, float precoCusto, float precoVenda, int estoqueAtual, boolean ativo, LocalDateTime dataCriacao, LocalDateTime dataUltimaAtualizacao) {
        super(nome, descricao, precoCusto, precoVenda, estoqueAtual, ativo, dataCriacao, dataUltimaAtualizacao);
    }
}
