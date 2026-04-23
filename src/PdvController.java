import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author nathan
 */
public class PdvController {
    
    public void createPdv(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os dados do novo PDV (Mercado): ");
        
        System.out.print("ID: ");
        int id = input.nextInt();
        input.nextLine(); // Limpar buffer
        
        System.out.print("Nome da Unidade: ");
        String nome = input.nextLine();
        
        System.out.print("Cidade: ");
        String cidade = input.nextLine();
        
        System.out.print("Estado (Sigla): ");
        String estado = input.next();
        input.nextLine(); // Limpar buffer
        
        System.out.print("Endereço Completo: ");
        String endereco = input.nextLine();
        
        PdvBean pb = new PdvBean(id, nome, cidade, estado, endereco);
        PdvModel.create(pb, con);
        System.out.println("PDV cadastrado com sucesso!!");
    }

    void listarPdvs(Connection con) throws SQLException {
        HashSet all = PdvModel.listAll(con);
        Iterator<PdvBean> it = all.iterator();
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    void removerPdv(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        listarPdvs(con);
        System.out.print("\nInforme o ID do PDV a remover: ");
        int id = input.nextInt();  
        PdvModel.remove(id, con);        
        System.out.println("PDV removido!");
    }

    void alterarPdv(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        listarPdvs(con);
        System.out.print("\nInforme o ID do PDV a alterar: ");
        int id = input.nextInt();
        input.nextLine(); // Limpar buffer
        
        System.out.print("Novo nome: ");
        String nome = input.nextLine();
        
        System.out.print("Nova cidade: ");
        String cidade = input.nextLine();
        
        System.out.print("Novo estado: ");
        String estado = input.next();
        input.nextLine(); // Limpar buffer
        
        System.out.print("Novo endereço: ");
        String endereco = input.nextLine();
        
        PdvBean pb = new PdvBean(id, nome, cidade, estado, endereco);
        PdvModel.alterar(pb, con);
        System.out.println("PDV atualizado!");
    }
}