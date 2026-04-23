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
public class PdvModel {

    public static void create(PdvBean p, Connection con) throws SQLException {
        PreparedStatement st;
            st = con.prepareStatement("INSERT INTO pdv (id, nome, cidade, estado, endereco) VALUES (?,?,?,?,?)");
            st.setInt(1, p.getId());
            st.setString(2, p.getNome());
            st.setString(3, p.getCidade());
            st.setString(4, p.getEstado());
            st.setString(5, p.getEndereco());
            st.execute();
            st.close();  
    }

    static HashSet listAll(Connection con) throws SQLException {
        Statement st;
        HashSet list = new HashSet();
            st = con.createStatement();
            String sql = "SELECT id, nome, cidade, estado, endereco FROM pdv ORDER BY id ASC";
            ResultSet result = st.executeQuery(sql);
            while(result.next()) {
                list.add(new PdvBean(
                    result.getInt(1), 
                    result.getString(2), 
                    result.getString(3), 
                    result.getString(4),
                    result.getString(5)
                ));
            }
        return list;
    }

    static void remove(int id, Connection con) throws SQLException {
        String sql = "DELETE FROM pdv WHERE id=?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, id);
        st.execute();
        st.close();
    }

    static void alterar(PdvBean p, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("UPDATE pdv SET nome=?, cidade=?, estado=?, endereco=? WHERE id=?");
        st.setString(1, p.getNome());
        st.setString(2, p.getCidade());
        st.setString(3, p.getEstado());
        st.setString(4, p.getEndereco());
        st.setInt(5, p.getId());
        st.execute();
        st.close();          
    }
}