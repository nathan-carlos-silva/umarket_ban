import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashSet;

/**
 *
 * @author nathan
 */
public class VendasModel {

    public static void create(VendasBean v, Connection con) throws SQLException {
        PreparedStatement st;
            st = con.prepareStatement("INSERT INTO vendas (id, data, valor, forma_pagamento, id_pdv, id_usuario) VALUES (?,?,?,?,?,?)");
            st.setInt(1, v.getId());
            st.setTimestamp(2, v.getData());
            st.setFloat(3, v.getValor());
            st.setString(4, v.getForma_pagamento());
            st.setInt(5, v.getId_pdv());
            st.setInt(6, v.getId_usuario());
            st.execute();
            st.close();  
    }

    static HashSet listAll(Connection con) throws SQLException {
        Statement st;
        HashSet list = new HashSet();
            st = con.createStatement();
            String sql = "SELECT id, data, valor, forma_pagamento, id_pdv, id_usuario FROM vendas ORDER BY id ASC";
            ResultSet result = st.executeQuery(sql);
            while(result.next()) {
                list.add(new VendasBean(
                    result.getInt(1), 
                    result.getTimestamp(2), 
                    result.getFloat(3), 
                    result.getString(4),
                    result.getInt(5),
                    result.getInt(6)
                ));
            }
        return list;
    }

    static void remove(int id, Connection con) throws SQLException {
        String sql = "DELETE FROM vendas WHERE id=?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, id);
        st.execute();
        st.close();
    }

    static void alterar(VendasBean v, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("UPDATE vendas SET valor=?, forma_pagamento=? WHERE id=?");
        st.setFloat(1, v.getValor());
        st.setString(2, v.getForma_pagamento());
        st.setInt(3, v.getId());
        st.execute();
        st.close();          
    }
}