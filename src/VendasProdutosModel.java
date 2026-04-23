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
public class VendasProdutosModel {

    public static void create(VendasProdutosBean vp, Connection con) throws SQLException {
        PreparedStatement st;
        // Removido id_usuario do SQL
        st = con.prepareStatement("INSERT INTO vendas_produtos (id, id_venda, id_produto, qtd_produto, valor_unitario, valor_total) VALUES (?,?,?,?,?,?)");
        st.setInt(1, vp.getId());
        st.setInt(2, vp.getId_venda());
        st.setInt(3, vp.getId_produto());
        st.setInt(4, vp.getQtd_produto());
        st.setFloat(5, vp.getValor_unitario());
        st.setFloat(6, vp.getValor_total());
        st.execute();
        st.close();  
    }

    static HashSet listAll(Connection con) throws SQLException {
        Statement st;
        HashSet list = new HashSet();
            st = con.createStatement();
            String sql = "SELECT * FROM vendas_produtos ORDER BY id ASC";
            ResultSet result = st.executeQuery(sql);
            while(result.next()) {
                list.add(new VendasProdutosBean(
                    result.getInt(1), result.getInt(2),
                    result.getInt(3), result.getInt(4), result.getFloat(5), result.getFloat(6)
                ));
            }
        return list;
    }

}