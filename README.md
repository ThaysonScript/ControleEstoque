# Projeto (ControleEstoque) de Orientação a Objetos com Foco em Trade-offs
<p>
  O trade-off literalmente significa vantagens/desvantagens, este projeto tem como foco definir as vantagens da arquitetura em camadas, padrão de design fachada, conceitos de orientação a objetos e as suas desvantagens e o porque de abordar tal conceito em um viés e não outro.
</p>

<hr>

<p>
  Este simples projeto poderá demonstrar insights valiosos sobre engenharia de controle de negócio, qualidade e manutenibilidade de código.
</p>

<hr>
<p>
  Exemplo pode ser consultado no livro de <i><strong>"Arthur J. Riel – Object-Oriented Design Heuristics."</strong></i>
</p>

## Estrutura de Diretórios Sugerida
```
src/
  ├── fachada/
  |      ├── Fachada.java
  |
  ├── apresentacao/
  │      ├── TelaCategorias.java
  |      ├── TelaProdutos.java
  |      ├── TelaRelatorios.java
  │
  ├── negocio/
  │      ├── entidade/
  |      |      ├── Categoria.java
  │      |      ├── Produto.java
  |      |      ├── ProdutoComNumeroSerie.java
  |      |      ├── ProdutoPerecivel.java
  |      |      ├── ProdutoComposto.java
  |      |      ├── ProdutoFracionado.java
  |      |      ├── IGeradorRelatorio.java
  |      |      ├── Relatorio.java
  │      │
  │      ├── servico/
  │      │      ├── Servicos.java
  │      |
  │      ├── repositorio/
  │      │      ├── Repositorio.java
  │      │
  │      ├── excecao/
  │      │      ├── Excecao.java
  |
  ├── Main.java

```
