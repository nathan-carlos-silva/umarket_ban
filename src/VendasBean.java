import java.sql.Timestamp;

/**
 *
 * @author nathan
 */
public class VendasBean {
   private int id;
   private Timestamp data;
   private float valor;
   private String forma_pagamento;
   private int id_pdv;
   private int id_usuario;

   public VendasBean(int id, Timestamp data, float valor, String forma_pagamento, int id_pdv, int id_usuario) {
       this.id = id;
       this.data = data;
       this.valor = valor;
       this.forma_pagamento = forma_pagamento;
       this.id_pdv = id_pdv;
       this.id_usuario = id_usuario;
   }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getForma_pagamento() {
        return forma_pagamento;
    }

    public void setForma_pagamento(String forma_pagamento) {
        this.forma_pagamento = forma_pagamento;
    }

    public int getId_pdv() {
        return id_pdv;
    }

    public void setId_pdv(int id_pdv) {
        this.id_pdv = id_pdv;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
   
    @Override
    public String toString(){
        return "Venda ID: " + id + " | Data: " + data + " | Valor: R$ " + valor + " | PDV: " + id_pdv + " | ID usuário: " + id_usuario;
    }
}