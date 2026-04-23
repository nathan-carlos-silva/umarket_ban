import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nathan
 */
public class UsuariosController {
    
    public void createUsuario(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Insira os seguintes dados para criar um novo Usuário: ");
        
        System.out.print("ID: ");
        int id = input.nextInt();
        input.nextLine(); // Consumir o enter
        
        System.out.print("Nome: ");
        String nome = input.nextLine();
        
        System.out.print("Email: ");
        String email = input.next();
        
        System.out.print("Data de Nascimento (AAAA-MM-DD): ");
        String data = input.next();
        
        System.out.print("CPF (somente números): ");
        long cpf = input.nextLong();
        
        UsuariosBean ub = new UsuariosBean(id, nome, email, data, cpf);
        UsuariosModel.create(ub, con);
        System.out.println("Usuário criado com sucesso!!");
    }

    void listarUsuarios(Connection con) throws SQLException {
        HashSet all = UsuariosModel.listAll(con);
        Iterator<UsuariosBean> it = all.iterator();
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }

    void removerUsuario(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        listarUsuarios(con);
        System.out.println("\nInforme o ID do usuário a remover: ");
        int id = input.nextInt();  
        UsuariosModel.remove(id, con);        
        System.out.println("Usuário removido com sucesso!");
    }

    void alterarUsuario(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        listarUsuarios(con);
        System.out.println("\nInforme o ID do usuário a alterar: ");
        int id = input.nextInt();
        input.nextLine(); // Consumir o enter
        
        System.out.print("Informe o novo nome: ");
        String nome = input.nextLine();
        
        System.out.print("Informe o novo email: ");
        String email = input.next();
        
        System.out.print("Informe a nova data de nascimento (AAAA-MM-DD): ");
        String data = input.next();
        
        System.out.print("Informe o novo CPF: ");
        long cpf = input.nextLong();
        
        UsuariosBean ub = new UsuariosBean(id, nome, email, data, cpf);
        UsuariosModel.alterar(ub, con);
        System.out.println("Usuário alterado com sucesso!");
    }
}