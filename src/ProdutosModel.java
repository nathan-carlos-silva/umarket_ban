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
public class ProdutosModel {

    public static void create(ProdutosBean p, Connection con) throws SQLException {
        PreparedStatement st;
            st = con.prepareStatement("INSERT INTO produtos (id, nome, classificacao, preco) VALUES (?,?,?,?)");
            st.setInt(1, p.getId());
            st.setString(2, p.getNome());
            st.setString(3, p.getClassificacao());
            st.setFloat(4, p.getPreco());
            st.execute();
            st.close();  
    }

    static HashSet listAll(Connection con) throws SQLException {
        Statement st;
        HashSet list = new HashSet();
            st = con.createStatement();
            String sql = "SELECT id, nome, classificacao, preco FROM produtos ORDER BY id ASC";
            ResultSet result = st.executeQuery(sql);
            while(result.next()) {
                list.add(new ProdutosBean(
                    result.getInt(1), 
                    result.getString(2), 
                    result.getString(3), 
                    result.getFloat(4)
                ));
            }
        return list;
    }

    static void remove(int id, Connection con) throws SQLException {
        String sql = "DELETE FROM produtos WHERE id=?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, id);
        st.execute();
        st.close();
    }

    static void alterar(ProdutosBean p, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("UPDATE produtos SET nome=?, classificacao=?, preco=? WHERE id=?");
        st.setString(1, p.getNome());
        st.setString(2, p.getClassificacao());
        st.setFloat(3, p.getPreco());
        st.setInt(4, p.getId());
        st.execute();
        st.close();          
    }
}