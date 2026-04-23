import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

/**
 *
 * @author nathan
 */
public class EstoqueModel {

    public static void create(EstoqueBean e, Connection con) throws SQLException {
        PreparedStatement st;
            st = con.prepareStatement("INSERT INTO estoque (id_pdv, id_produto, qtd_atual, capacidade) VALUES (?,?,?,?)");
            st.setInt(1, e.getId_pdv());
            st.setInt(2, e.getId_produto());
            st.setInt(3, e.getQtd_atual());
            st.setInt(4, e.getCapacidade());
            st.execute();
            st.close();  
    }

    static HashSet listAll(Connection con) throws SQLException {
        Statement st;
        HashSet list = new HashSet();
            st = con.createStatement();
            String sql = "SELECT id_pdv, id_produto, qtd_atual, capacidade FROM estoque ORDER BY id_pdv ASC, id_produto ASC";
            ResultSet result = st.executeQuery(sql);
            while(result.next()) {
                list.add(new EstoqueBean(
                    result.getInt(1), 
                    result.getInt(2), 
                    result.getInt(3), 
                    result.getInt(4)
                ));
            }
        return list;
    }

    static void remove(int id_pdv, int id_produto, Connection con) throws SQLException {
        String sql = "DELETE FROM estoque WHERE id_pdv=? AND id_produto=?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, id_pdv);
        st.setInt(2, id_produto);
        st.execute();
        st.close();
    }

    static void alterar(EstoqueBean e, Connection con) throws SQLException {
        PreparedStatement st;
        // Altera apenas a quantidade e a capacidade para um par PDV/Produto existente
        st = con.prepareStatement("UPDATE estoque SET qtd_atual=?, capacidade=? WHERE id_pdv=? AND id_produto=?");
        st.setInt(1, e.getQtd_atual());
        st.setInt(2, e.getCapacidade());
        st.setInt(3, e.getId_pdv());
        st.setInt(4, e.getId_produto());
        st.execute();
        st.close();          
    }
}