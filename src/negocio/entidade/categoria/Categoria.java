package negocio.entidade.categoria;

import negocio.excecoes.DadosInvalidosException;

import java.util.Objects;

public class Categoria {
    private int id;
    private String nome;
    private String descricao;
    private boolean ativa;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws DadosInvalidosException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new DadosInvalidosException("O nome da categoria não pode ser nulo ou vazio.");
        }
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void desativar() {
        this.ativa = false;
    }

    public void ativar() {
        this.ativa = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return id == categoria.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nome: '" + nome + "' | Status: " + (ativa ? "Ativa" : "Inativa");
    }

    public Categoria(String nome, String descricao) throws DadosInvalidosException {
        this.setNome(nome);
        this.setDescricao(descricao);
        this.ativa = true;
    }
}