import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nathan
 */
public class UsuariosModel {

    public static void create(UsuariosBean u, Connection con) throws SQLException {
        PreparedStatement st;
            st = con.prepareStatement("INSERT INTO usuarios (id, nome, email, data_nasc, cpf) VALUES (?,?,?,?,?)");
            st.setInt(1, u.getId());
            st.setString(2, u.getNome());
            st.setString(3, u.getEmail());
            // Converte a String do Bean para o tipo Date do SQL
            st.setDate(4, Date.valueOf(u.getData_nasc())); 
            st.setLong(5, u.getCpf());
            st.execute();
            st.close();  
    }

    static HashSet listAll(Connection con) throws SQLException {
        Statement st;
        HashSet list = new HashSet();
            st = con.createStatement();
            String sql = "SELECT id, nome, email, data_nasc, cpf FROM usuarios ORDER BY id ASC";
            ResultSet result = st.executeQuery(sql);
            while(result.next()) {
                list.add(new UsuariosBean(
                    result.getInt(1), 
                    result.getString(2), 
                    result.getString(3), 
                    result.getDate(4).toString(), 
                    result.getLong(5)
                ));
            }
        return list;
    }

    static void remove(int id, Connection con) throws SQLException {
        String sql = "DELETE FROM usuarios WHERE id=?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, id);
        st.execute();
        st.close();
    }

    static void alterar(UsuariosBean u, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("UPDATE usuarios SET nome=?, email=?, data_nasc=?, cpf=? WHERE id=?");
        st.setString(1, u.getNome());
        st.setString(2, u.getEmail());
        st.setDate(3, Date.valueOf(u.getData_nasc()));
        st.setLong(4, u.getCpf());
        st.setInt(5, u.getId());
        st.execute();
        st.close();          
    }
}