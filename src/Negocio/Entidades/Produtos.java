package Negocio.Entidades;

public class Produtos {
    private String nome;
    private String descricao;
    private float preco;

    private void setNome(String nome) {
        this.nome = nome;
    }

    private void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    private void setPreco(float preco) {
        this.preco = preco;
    }

    public String getNome() {
        return this.nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public float getPreco() {
        return this.preco;
    }

    public Produtos(String nome, String descricao, float preco) {
        setNome(nome);
        setDescricao(descricao);
        setPreco(preco);
    }

}
