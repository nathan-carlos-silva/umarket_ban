/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nathan
 */
public class ProdutosBean {
   private int id;
   private String nome;
   private String classificacao;
   private float preco;

   public ProdutosBean(int id, String nome, String classificacao, float preco) {
       this.id = id;
       this.nome = nome;
       this.classificacao = classificacao;
       this.preco = preco;
   }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
   
    @Override
    public String toString(){
        return "ID: " + id + " | Produto: " + nome + " | Categoria: " + classificacao + " | Preço: R$ " + preco;
    }
}