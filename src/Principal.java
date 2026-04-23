import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author nathan
 */
public class Principal {

    public static void main(String[] args) throws SQLException {
        Conexao c = new Conexao();
        Connection con = c.getConnection();
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        Scanner input = new Scanner(System.in, StandardCharsets.UTF_8.name());
        //Scanner input = new Scanner(System.in);
        int op = 0;

        do {
            op = menuPrincipal();
            try {
                switch (op) {
                    case 1: menuListar(con, input); break;
                    case 2: menuAdicionar(con, input); break;
                    case 3: menuRemover(con, input); break;
                    case 4: menuAtualizar(con, input); break;
                    case 5: menuRelatorios(con, input); break;
                    case 6: System.out.println("Encerrando aplicação..."); break;
                }
            } catch (SQLException ex) {
                System.out.println("Erro: " + ex.getMessage());
            }
        } while (op != 6);

        con.close();
    }

    private static int menuPrincipal() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n===== UMARKET - MENU PRINCIPAL =====");
        System.out.println("1 - Listar registros");
        System.out.println("2 - Adicionar registros");
        System.out.println("3 - Remover registros");
        System.out.println("4 - Atualizar registros");
        System.out.println("5 - Exibir relatórios");
        System.out.println("6 - Encerrar Aplicação");
        System.out.print("Sua opção: ");
        return input.nextInt();
    }

    // --- SUBMENUS ---

    private static void menuListar(Connection con, Scanner input) throws SQLException {
        System.out.println("\n--- LISTAR REGISTROS EM ---");
        System.out.println("1 - Usuários | 2 - Produtos | 3 - PDVs | 4 - Estoque | 5 - Vendas | 6 - Itens Vendas | 7 - Voltar");
        int sub = input.nextInt();
        switch (sub) {
            case 1: new UsuariosController().listarUsuarios(con); break;
            case 2: new ProdutosController().listarProdutos(con); break;
            case 3: new PdvController().listarPdvs(con); break;
            case 4: new EstoqueController().listarEstoque(con); break;
            case 5: new VendasController().listarVendas(con); break;
            case 6: new VendasProdutosController().listarItensVendas(con); break;
        }
    }

    private static void menuAdicionar(Connection con, Scanner input) throws SQLException {
        System.out.println("\n--- ADICIONAR REGISTRO EM ---");
        System.out.println("1 - Usuários | 2 - Produtos | 3 - PDVs | 4 - Estoque | 5 - Itens Venda | 6 - Voltar");
        int sub = input.nextInt();
        switch (sub) {
            case 1: new UsuariosController().createUsuario(con); break;
            case 2: new ProdutosController().createProduto(con); break;
            case 3: new PdvController().createPdv(con); break;
            case 4: new EstoqueController().createEstoque(con); break;
            case 5: new VendasProdutosController().createItemVenda(con); break;
        }
    }

    private static void menuRemover(Connection con, Scanner input) throws SQLException {
        System.out.println("\n--- REMOVER REGISTRO EM ---");
        System.out.println("1 - Usuários | 2 - Produtos | 3 - PDVs | 4 - Estoque | 5 - Voltar");
        int sub = input.nextInt();
        switch (sub) {
            case 1: new UsuariosController().removerUsuario(con); break;
            case 2: new ProdutosController().removerProduto(con); break;
            case 3: new PdvController().removerPdv(con); break;
            case 4: new EstoqueController().removerEstoque(con); break;
        }
    }

    private static void menuAtualizar(Connection con, Scanner input) throws SQLException {
        System.out.println("\n--- ATUALIZAR REGISTRO EM ---");
        System.out.println("1 - Usuários | 2 - Produtos | 3 - PDVs | 4 - Estoque | 5 - Voltar");
        int sub = input.nextInt();
        switch (sub) {
            case 1: new UsuariosController().alterarUsuario(con); break;
            case 2: new ProdutosController().alterarProduto(con); break;
            case 3: new PdvController().alterarPdv(con); break;
            case 4: new EstoqueController().alterarEstoque(con); break;
        }
    }
    
    private static void menuRelatorios(Connection con, Scanner input) throws SQLException {
        System.out.println("\n--- RELATÓRIOS GERENCIAIS ---");
        System.out.println("1 - Top 5 Produtos vendidos (quantidade)");
        System.out.println("2 - Valor vendido por dia (Últimos 10 dias)");
        System.out.println("3 - Valor total vendido por PDV");
        System.out.println("4 - Voltar ao menu principal");
        System.out.print("Sua opção: ");

        int sub = input.nextInt();
        RelatoriosController rc = new RelatoriosController();

        switch (sub) {
            case 1: rc.top5Produtos(con); break;
            case 2: rc.valorVendidoUltimos10Dias(con); break;
            case 3: rc.valorTotalPorPdv(con); break;
        }
    }
}