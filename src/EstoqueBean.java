/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nathan
 */
public class EstoqueBean {
   private int id_pdv;
   private int id_produto;
   private int qtd_atual;
   private int capacidade;

   public EstoqueBean(int id_pdv, int id_produto, int qtd_atual, int capacidade) {
       this.id_pdv = id_pdv;
       this.id_produto = id_produto;
       this.qtd_atual = qtd_atual;
       this.capacidade = capacidade;
   }

    public int getId_pdv() {
        return id_pdv;
    }

    public void setId_pdv(int id_pdv) {
        this.id_pdv = id_pdv;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public int getQtd_atual() {
        return qtd_atual;
    }

    public void setQtd_atual(int qtd_atual) {
        this.qtd_atual = qtd_atual;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
   
    @Override
    public String toString(){
        return "PDV ID: " + id_pdv + " | Produto ID: " + id_produto + " | Qtd: " + qtd_atual + "/" + capacidade;
    }
}