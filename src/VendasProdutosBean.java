/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nathan
 */
public class VendasProdutosBean {
   private int id;
   private int id_venda;
   private int id_produto;
   private int qtd_produto;
   private float valor_unitario;
   private float valor_total;

   public VendasProdutosBean(int id, int id_venda, int id_produto, int qtd_produto, float valor_unitario, float valor_total) {
       this.id = id;
       this.id_venda = id_venda;
       this.id_produto = id_produto;
       this.qtd_produto = qtd_produto;
       this.valor_unitario = valor_unitario;
       this.valor_total = valor_total;
   }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getId_venda() { return id_venda; }
    public void setId_venda(int id_venda) { this.id_venda = id_venda; }

    public int getId_produto() { return id_produto; }
    public void setId_produto(int id_produto) { this.id_produto = id_produto; }

    public int getQtd_produto() { return qtd_produto; }
    public void setQtd_produto(int qtd_produto) { this.qtd_produto = qtd_produto; }

    public float getValor_unitario() { return valor_unitario; }
    public void setValor_unitario(float valor_unitario) { this.valor_unitario = valor_unitario; }

    public float getValor_total() { return valor_total; }
    public void setValor_total(float valor_total) { this.valor_total = valor_total; }
   
    @Override
    public String toString(){
        return "Item ID: " + id + " | Venda: " + id_venda + " | Prod: " + id_produto + " | Qtd: " + qtd_produto + " | Valor unitário: " + valor_unitario + " | Total: R$ " + valor_total;
    }
}