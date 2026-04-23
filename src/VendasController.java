import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author nathan
 */
public class VendasController {
    

    void listarVendas(Connection con) throws SQLException {
        HashSet all = VendasModel.listAll(con);
        Iterator<VendasBean> it = all.iterator();
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    void removerVenda(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        listarVendas(con);
        System.out.print("\nInforme o ID da venda a remover: ");
        int id = input.nextInt();  
        VendasModel.remove(id, con);        
        System.out.println("Venda removida!");
    }
}