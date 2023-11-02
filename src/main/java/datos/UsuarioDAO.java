package datos;

import domain.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO Data Access Object (Objeto de acceso a datos) de usuario su función
 * será hacer la funcionalidad para CRUD de la tabla usuarios
 *
 * @author BETO
 */
public class UsuarioDAO {

    //constante que contiene sentencia para seleccionar todos los registros de la tabla
    private static final String SQL_SELECT = "SELECT * FROM usuarios";

    //costante que contiene sentencia para insertar un nuevo registro
    private static final String SQL_INSERT = "INSERT INTO usuarios (usuario, password) VALUES(?, ?)";

    //constante que contiene sentencia para actualizar un registro
    private static final String SQL_UPDATE = "UPDATE usuarios SET usuario = ?, password= ? WHERE id = ?";

    //constante que contiene sentencia para eliminar un registro
    private static final String SQL_DELETE = "DELETE FROM usuarios WHERE ID = ?";

    /**
     * Método que recuperará los registros de la tabla usuarios
     *
     * @return lista con el resultado de la query
     */
    public List<Usuario> listar() {
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        Usuario usuario = null;
        List<Usuario> usuarios = new ArrayList<>();

        try {
            conexion = Conexion.getConnection();
            sentencia = conexion.prepareStatement(SQL_SELECT);
            resultado = sentencia.executeQuery();

            while (resultado.next()) {
                //secuperando los datos de la consulta
                int id = resultado.getInt("id");
                String nombre = resultado.getString("usuario");
                String contrasenia = resultado.getString("password");

                //nuevo objeto
                usuario = new Usuario(id, nombre, contrasenia);

                //agregamos la referencia del objeto a la lista
                usuarios.add(usuario);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            //cerramos los objetos
            Conexion.close(resultado);
            Conexion.close(sentencia);
            Conexion.close(conexion);

        }

        return usuarios;
    }

    /**
     * Método que se encargará de agregar un nuevo registro
     *
     * @param usuario que será agregado como registro
     * @return numero de registros modificados
     */
    public static int agregar(Usuario usuario) {
        Connection conexion = null;
        PreparedStatement sentencia = null;
        int registros = 0;

        try {
            conexion = Conexion.getConnection();
            sentencia = conexion.prepareStatement(SQL_INSERT);
            sentencia.setString(1, usuario.getUsuario());
            sentencia.setString(2, usuario.getContrasenia());
            registros = sentencia.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            // cerramos los objetos
            Conexion.close(sentencia);
            Conexion.close(conexion);
        }

        return registros;
    }

    /**
     * Método que se encargará de actualizar registros
     *
     * @param usuario que será actualizado en el registro de la tabla
     * @return número de registros alterados
     */
    public static int actualizar(Usuario usuario) {
        Connection conexion = null;
        PreparedStatement sentencia = null;
        int registros = 0;

        try {
            conexion = Conexion.getConnection();
            sentencia = conexion.prepareStatement(SQL_UPDATE);
            sentencia.setString(1, usuario.getUsuario());
            sentencia.setString(2, usuario.getContrasenia());
            sentencia.setInt(3, usuario.getId());
            registros = sentencia.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            // cerramos los objetos
            Conexion.close(sentencia);
            Conexion.close(conexion);
        }

        return registros;
    }

    /**
     * Métod que se encargará de eliminar un registro
     *
     * @param usuario que será eliminado
     * @return número de registros alterados
     */
    public static int eliminar(Usuario usuario) {
        Connection conexion = null;
        PreparedStatement sentencia = null;
        int registros = 0;

        try {
            conexion = Conexion.getConnection();
            sentencia = conexion.prepareStatement(SQL_DELETE);
            sentencia.setInt(1, usuario.getId());
            registros = sentencia.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            // cerramos los objetos
            Conexion.close(sentencia);
            Conexion.close(conexion);
        }

        return registros;
    }
}
