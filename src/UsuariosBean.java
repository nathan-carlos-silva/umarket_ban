/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nathan
 */
public class UsuariosBean {
   private int id;
   private String nome;
   private String email;
   private String data_nasc; // Usando String para simplificar a entrada via Scanner como no projeto base
   private long cpf;

   public UsuariosBean(int id, String nome, String email, String data_nasc, long cpf) {
       this.id = id;
       this.nome = nome;
       this.email = email;
       this.data_nasc = data_nasc;
       this.cpf = cpf;
   }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the data_nasc
     */
    public String getData_nasc() {
        return data_nasc;
    }

    /**
     * @param data_nasc the data_nasc to set
     */
    public void setData_nasc(String data_nasc) {
        this.data_nasc = data_nasc;
    }

    /**
     * @return the cpf
     */
    public long getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(long cpf) {
        this.cpf = cpf;
    }
   
    @Override
    public String toString(){
        return "ID: " + id + " | Nome: " + nome + " | Email: " + email + " | CPF: " + cpf;
    }
}