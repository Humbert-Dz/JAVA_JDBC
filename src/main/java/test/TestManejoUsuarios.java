package test;

import datos.UsuarioDAO;
import domain.Usuario;
import java.util.List;

/**
 *
 * @author BETO
 */
public class TestManejoUsuarios {

    public static void main(String[] args) {

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        imprimir(usuarioDAO);

        // agregar un nuevo registro
        //Usuario usuario = new Usuario("mono", "elbciho");
        //usuarioDAO.agregar(usuario);
        
        //actualizar registro con id 1
        //Usuario usuario2 = new Usuario(1, "Martin", "messi123");
        //usuarioDAO.actualizar(usuario2);
        
        Usuario usuario2 = new Usuario(1, "Martin", "messi123");
        usuarioDAO.eliminar(usuario2);
        

        imprimir(usuarioDAO);
    }

    public static void imprimir(UsuarioDAO usuarioDAO) {
        List<Usuario> usuarios = usuarioDAO.listar();

        usuarios.forEach(usuario -> {
            System.out.println(usuario);
        });

        System.out.println("");
    }

}
