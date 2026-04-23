import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author nathan
 */
public class EstoqueController {
    
    public void createEstoque(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("--- Cadastro de Estoque ---");
        
        // Opcional: Chamar listarPdvs e listarProdutos aqui para ajudar o usuário
        System.out.print("ID do PDV: ");
        int id_pdv = input.nextInt();
        
        System.out.print("ID do Produto: ");
        int id_produto = input.nextInt();
        
        System.out.print("Quantidade Inicial: ");
        int qtd = input.nextInt();
        
        System.out.print("Capacidade Máxima: ");
        int cap = input.nextInt();
        
        EstoqueBean eb = new EstoqueBean(id_pdv, id_produto, qtd, cap);
        EstoqueModel.create(eb, con);
        System.out.println("Estoque registrado com sucesso!");
    }

    void listarEstoque(Connection con) throws SQLException {
        HashSet all = EstoqueModel.listAll(con);
        Iterator<EstoqueBean> it = all.iterator();
        System.out.println("\n--- Itens em Estoque ---");
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    void removerEstoque(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        listarEstoque(con);
        System.out.println("\nInforme o ID do PDV: ");
        int id_pdv = input.nextInt();
        System.out.println("Informe o ID do Produto: ");
        int id_prod = input.nextInt();
        
        EstoqueModel.remove(id_pdv, id_prod, con);        
        System.out.println("Item removido do estoque!");
    }

    void alterarEstoque(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        listarEstoque(con);
        System.out.print("\nID do PDV: ");
        int id_pdv = input.nextInt();
        System.out.print("ID do Produto: ");
        int id_prod = input.nextInt();
        
        System.out.print("Nova Quantidade Atual: ");
        int qtd = input.nextInt();
        System.out.print("Nova Capacidade: ");
        int cap = input.nextInt();
        
        EstoqueBean eb = new EstoqueBean(id_pdv, id_prod, qtd, cap);
        EstoqueModel.alterar(eb, con);
        System.out.println("Estoque atualizado!");
    }
}