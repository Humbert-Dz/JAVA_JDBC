package test;

import datos.*;
import domain.Usuario;
import java.sql.*;
import java.util.List;

/**
 *
 * @author BETO
 */
public class TestManejoUsuarios {

    public static void main(String[] args) {

        Connection conexion = null;
        try {
            conexion = Conexion.getConnection();

            //verificamos si la conexion está en autocommit para desactivarlo
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }
            
            
            UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);

            imprimir(usuarioDAO);

            Usuario usuario = new Usuario("beto1312", "ejemplo de contrasena");
            usuarioDAO.agregar(usuario);
            
            //usuario a modificar
            //Usuario usuario2 = new Usuario(2, "mono", "elbciho");
            Usuario usuario2 = new Usuario(2, "Martin", "messi123");
            usuarioDAO.actualizar(usuario2);

            conexion.commit();
            System.out.println("Commit de cambios ejecutado!");
                    
            imprimir(usuarioDAO);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            try {
                conexion.rollback();
                System.out.println("Rollback ejecutado!");
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }

    }

    public static void imprimir(UsuarioDAO usuarioDAO) throws SQLException {
        List<Usuario> usuarios = usuarioDAO.listar();

        usuarios.forEach(usuario -> {
            System.out.println(usuario);
        });

        System.out.println("");
    }

}
