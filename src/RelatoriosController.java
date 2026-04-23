import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author nathan
 */
public class RelatoriosController {

    // 1 - Top 5 Produtos vendidos (quantidade)
    public void top5Produtos(Connection con) throws SQLException {
        String sql = "SELECT p.nome, SUM(vp.qtd_produto) as total_qtd " +
                     "FROM vendas_produtos vp " +
                     "JOIN produtos p ON vp.id_produto = p.id " +
                     "GROUP BY p.nome " +
                     "ORDER BY total_qtd DESC LIMIT 5 ";
        
        System.out.println("\n--- TOP 5 PRODUTOS MAIS VENDIDOS ---");
        PreparedStatement st = con.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            System.out.println("Produto: " + rs.getString("nome") + " | Qtd: " + rs.getInt("total_qtd"));
        }
    }

    // 2 - Valor vendido por dia nos últimos 10 dias
    public void valorVendidoUltimos10Dias(Connection con) throws SQLException {
        String sql = "SELECT data::date as dia, SUM(valor) as total_dia " +
                     "FROM vendas " +
                     "GROUP BY CAST(data AS DATE) " +
                     "ORDER BY dia DESC LIMIT 10";

        System.out.println("\n--- FATURAMENTO DOS ÚLTIMOS 10 DIAS ---");
        PreparedStatement st = con.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            System.out.println("Data: " + rs.getDate("dia") + " | Total: R$ " + rs.getFloat("total_dia"));
        }
    }

    // 3 - Valor total vendido por PDV
    public void valorTotalPorPdv(Connection con) throws SQLException {
        String sql = "SELECT v.id_pdv, p.nome, SUM(v.valor) as total_vendas " +
                     "FROM vendas v " +
                     "JOIN pdv p ON v.id_pdv = p.id " +
                     "GROUP BY v.id_pdv, p.nome " +
                     "ORDER BY total_vendas DESC";

        System.out.println("\n--- TOTAL VENDIDO POR PDV ---");
        PreparedStatement st = con.prepareStatement(sql);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            System.out.println("PDV: " + rs.getInt("id_pdv") + " (" + rs.getString("nome") + ") | Total: R$ " + rs.getFloat("total_vendas"));
        }
    }
}