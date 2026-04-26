/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nathan
 */
public class PdvBean {
   private int id;
   private String nome;
   private String cidade;
   private String estado;
   private String endereco;

   public PdvBean(int id, String nome, String cidade, String estado, String endereco) {
       this.id = id;
       this.nome = nome;
       this.cidade = cidade;
       this.estado = estado;
       this.endereco = endereco;
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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
   
    @Override
    public String toString(){
        return "ID: " + id + " | Nome: " + nome + " | Local: " + cidade + "/" + estado + " | Endereço: " + endereco;
    }
}