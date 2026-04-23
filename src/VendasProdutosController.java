import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.sql.*;
import java.util.Scanner;

/**
 * @author nathan
 */
public class VendasProdutosController {

    public void createItemVenda(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        int proximoIdVenda = -1; // Inicializado para controle de erro

        try {
            // 1. DADOS INICIAIS DA VENDA
            System.out.println("\n--- INICIANDO NOVA VENDA ---");
            new UsuariosController().listarUsuarios(con);
            System.out.print("Informe o ID do seu usuário: ");
            int idUser = input.nextInt();
            
            new PdvController().listarPdvs(con);
            System.out.print("Informe o ID do seu ponto de venda atual: ");
            int idPdv = input.nextInt();

            System.out.println("Forma de Pagamento: 1-PIX | 2-Débito | 3-Crédito");
            int opPag = input.nextInt();
            String fPag = (opPag == 1) ? "PIX" : (opPag == 2) ? "Débito" : "Crédito";

            // 2. GERAR ID E CRIAR CABEÇALHO (Ponto crítico)
            Statement st = con.createStatement();
            ResultSet rsIdVenda = st.executeQuery("SELECT COALESCE(MAX(id), 0) + 1 FROM vendas");
            if (rsIdVenda.next()) proximoIdVenda = rsIdVenda.getInt(1);

            Timestamp agora = new Timestamp(System.currentTimeMillis());
            VendasBean vb = new VendasBean(proximoIdVenda, agora, 0, fPag, idPdv, idUser);
            VendasModel.create(vb, con);

            float valorTotalVenda = 0;
            char continuar = 's';

            // 3. LOOP DE ITENS
            while (continuar == 's' || continuar == 'S') {
                System.out.println("\n--- ADICIONAR ITEM À VENDA #" + proximoIdVenda + " ---");
                new ProdutosController().listarProdutos(con);
                System.out.print("ID do Produto: ");
                int idProd = input.nextInt();

                // Validação de existência do produto
                PreparedStatement stProd = con.prepareStatement("SELECT nome, preco FROM produtos WHERE id = ?");
                stProd.setInt(1, idProd);
                ResultSet rsProd = stProd.executeQuery();

                if (!rsProd.next()) {
                    throw new SQLException("Produto ID " + idProd + " não encontrado! Venda cancelada.");
                }
                
                float precoUnitario = rsProd.getFloat("preco");
                String nomeProd = rsProd.getString("nome");

                // Validação de estoque
                PreparedStatement stEstoque = con.prepareStatement(
                    "SELECT qtd_atual FROM estoque WHERE id_pdv = ? AND id_produto = ?");
                stEstoque.setInt(1, idPdv);
                stEstoque.setInt(2, idProd);
                ResultSet rsEstoque = stEstoque.executeQuery();

                if (!rsEstoque.next()) {
                    throw new SQLException("Produto " + nomeProd + " não possui registro de estoque neste PDV!");
                }

                int qtdDisponivel = rsEstoque.getInt("qtd_atual");
                System.out.print("Quantidade (Disponível: " + qtdDisponivel + "): ");
                int qtdDesejada = input.nextInt();

                if (qtdDesejada > qtdDisponivel || qtdDesejada <= 0) {
                    throw new SQLException("Quantidade inválida ou insuficiente (Disponível: " + qtdDisponivel + ")!");
                }

                // CÁLCULOS E INSERÇÃO DO ITEM
                float totalItem = precoUnitario * qtdDesejada;
                valorTotalVenda += totalItem;

                ResultSet rsIdItem = st.executeQuery("SELECT COALESCE(MAX(id), 0) + 1 FROM vendas_produtos");
                int proximoIdItem = rsIdItem.next() ? rsIdItem.getInt(1) : 1;

                VendasProdutosBean vp = new VendasProdutosBean(proximoIdItem, proximoIdVenda, idProd, qtdDesejada, precoUnitario, totalItem);
                VendasProdutosModel.create(vp, con);

                // REDUÇÃO DO ESTOQUE
                PreparedStatement updateEstoque = con.prepareStatement(
                    "UPDATE estoque SET qtd_atual = qtd_atual - ? WHERE id_pdv = ? AND id_produto = ?");
                updateEstoque.setInt(1, qtdDesejada);
                updateEstoque.setInt(2, idPdv);
                updateEstoque.setInt(3, idProd);
                updateEstoque.executeUpdate();

                System.out.println("Item " + nomeProd + " adicionado!");
                System.out.print("Deseja adicionar mais um produto? (s/n): ");
                continuar = input.next().charAt(0);
            }

            // 4. FINALIZAÇÃO
            PreparedStatement updateVenda = con.prepareStatement("UPDATE vendas SET valor = ? WHERE id = ?");
            updateVenda.setFloat(1, valorTotalVenda);
            updateVenda.setInt(2, proximoIdVenda);
            updateVenda.executeUpdate();

            System.out.println("\nVENDA #" + proximoIdVenda + " FINALIZADA: R$ " + valorTotalVenda);

        } catch (SQLException e) {
            System.err.println("\n[ERRO NA VENDA] " + e.getMessage());
            
            // ROLLBACK MANUAL: Se o cabeçalho foi criado, deletamos para não deixar lixo
            if (proximoIdVenda != -1) {
                System.out.println("Limpando registros da venda abortada...");
                PreparedStatement stCleanProd = con.prepareStatement("DELETE FROM vendas_produtos WHERE id_venda = ?");
                stCleanProd.setInt(1, proximoIdVenda);
                stCleanProd.executeUpdate();

                PreparedStatement stCleanVenda = con.prepareStatement("DELETE FROM vendas WHERE id = ?");
                stCleanVenda.setInt(1, proximoIdVenda);
                stCleanVenda.executeUpdate();
            }
            // O erro não sobe para o Main, o catch trata e a função encerra, voltando ao menu.
        }
    }

    void listarItensVendas(Connection con) throws SQLException {
        HashSet all = VendasProdutosModel.listAll(con);
        Iterator<VendasProdutosBean> it = all.iterator();
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }
}