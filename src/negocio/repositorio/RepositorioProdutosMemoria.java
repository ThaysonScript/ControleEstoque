package negocio.repositorio;

import negocio.entidade.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class RepositorioProdutosMemoria implements IRepositorioProdutos {
    private final List<Produto> produtos;
    private static int proximoId = 1;

    public RepositorioProdutosMemoria() {
        this.produtos = new ArrayList<>();
    }

    @Override
    public void salvar(Produto produto) {
        if (produto.getId() == 0) {
            produto.setId(proximoId++);
            this.produtos.add(produto);
        } else {
            this.remover(produto.getId());
            this.produtos.add(produto);
        }
    }

    @Override
    public Produto buscarPorId(int id) {
        for (Produto produto : this.produtos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null; // Retorna null se não encontrar
    }

    @Override
    public boolean remover(int id) {
        return this.produtos.removeIf(p -> p.getId() == id);
    }

    @Override
    public List<Produto> listarTodos() {
        return new ArrayList<>(this.produtos);
    }

    @Override
    public List<Produto> listarEstoqueBaixo() {
        List<Produto> estoqueBaixo = new ArrayList<>();
        for (Produto produto : this.produtos) {
            if (produto.verificarEstoqueBaixo()) {
                estoqueBaixo.add(produto);
            }
        }
        return estoqueBaixo;
    }

    @Override
    public Produto buscarPorNome(String nome) {
        for (Produto produto : this.produtos) {
            if (produto.getNome().equalsIgnoreCase(nome)) {
                return produto;
            }
        }
        return null;
    }

    @Override
    public List<Produto> listarAbaixoDe(int limite) {
        return this.produtos.stream()
                .filter(p -> p.getQuantidadeDisponivel() < limite)
                .collect(Collectors.toList());
    }
}