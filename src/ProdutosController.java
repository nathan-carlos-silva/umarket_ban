import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author nathan
 */
public class ProdutosController {
    
    public void createProduto(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os dados do novo Produto: ");
        
        System.out.print("ID: ");
        int id = input.nextInt();
        input.nextLine(); // Limpar buffer
        
        System.out.print("Nome do Produto: ");
        String nome = input.nextLine();
        
        System.out.print("Classificação/Categoria: ");
        String classificacao = input.nextLine();
        
        System.out.print("Preço (use vírgula ou ponto conforme seu sistema): ");
        float preco = input.nextFloat();
        
        ProdutosBean pb = new ProdutosBean(id, nome, classificacao, preco);
        ProdutosModel.create(pb, con);
        System.out.println("Produto cadastrado com sucesso!!");
    }

    void listarProdutos(Connection con) throws SQLException {
        HashSet all = ProdutosModel.listAll(con);
        Iterator<ProdutosBean> it = all.iterator();
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    void removerProduto(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        listarProdutos(con);
        System.out.println("\nInforme o ID do produto a remover: ");
        int id = input.nextInt();  
        ProdutosModel.remove(id, con);        
        System.out.println("Produto removido!");
    }

    void alterarProduto(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        listarProdutos(con);
        System.out.print("\nInforme o ID do produto a alterar: ");
        int id = input.nextInt();
        input.nextLine(); // Limpar buffer
        
        System.out.print("Novo nome: ");
        String nome = input.nextLine();
        
        System.out.print("Nova classificação: ");
        String classificacao = input.nextLine();
        
        System.out.print("Novo preço: ");
        float preco = input.nextFloat();
        
        ProdutosBean pb = new ProdutosBean(id, nome, classificacao, preco);
        ProdutosModel.alterar(pb, con);
        System.out.println("Produto atualizado!");
    }
}