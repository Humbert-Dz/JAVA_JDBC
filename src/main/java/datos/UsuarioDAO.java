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

    //atributo de tipo Connection que permite conectar a la base de datos
    private Connection conexion;

    //constante que contiene sentencia para seleccionar todos los registros de la tabla
    private static final String SQL_SELECT = "SELECT * FROM usuarios";

    //costante que contiene sentencia para insertar un nuevo registro
    private static final String SQL_INSERT = "INSERT INTO usuarios (usuario, password) VALUES(?, ?)";

    //constante que contiene sentencia para actualizar un registro
    private static final String SQL_UPDATE = "UPDATE usuarios SET usuario = ?, password= ? WHERE id = ?";

    //constante que contiene sentencia para eliminar un registro
    private static final String SQL_DELETE = "DELETE FROM usuarios WHERE ID = ?";

    //constructor vacío
    public UsuarioDAO() {
    }

    /**
     * Constructor con parámetro
     *
     * @param conexion conexión a la base de datos
     */
    public UsuarioDAO(Connection conexion) {
        this.conexion = conexion;
    }

    /**
     *
     * Método que recuperará los registros de la tabla usuarios
     *
     * @return lista con el resultado de la query
     * @throws SQLException propagamos la posible excepción
     */
    public List<Usuario> listar() throws SQLException {
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        Usuario usuario = null;
        List<Usuario> usuarios = new ArrayList<>();

        try {
            //evaluando como se hará la conexión a la base de datos
            conexion = (this.conexion != null) ? this.conexion : Conexion.getConnection();
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

        } finally {
            //cerramos los objetos
            Conexion.close(resultado);
            Conexion.close(sentencia);
            if (this.conexion == null) {
                Conexion.close(conexion);
            }

        }

        return usuarios;
    }

    /**
     * Método que se encargará de agregar un nuevo registro
     *
     * @param usuario que será agregado como registro
     * @return numero de registros modificados
     * @throws SQLException propagamos la posible excepción
     */
    public int agregar(Usuario usuario) throws SQLException {
        Connection conexion = null;
        PreparedStatement sentencia = null;
        int registros = 0;

        try {
            //evaluando como se hará la conexión a la base de datos
            conexion = (this.conexion != null) ? this.conexion : Conexion.getConnection();
            sentencia = conexion.prepareStatement(SQL_INSERT);
            sentencia.setString(1, usuario.getUsuario());
            sentencia.setString(2, usuario.getContrasenia());
            registros = sentencia.executeUpdate();

        } finally {
            // cerramos los objetos
            Conexion.close(sentencia);
            //si la conexión se hizo por el método estático getConnection de Conexion
            if (this.conexion == null) {
                Conexion.close(conexion);
            }
        }

        return registros;
    }

    /**
     * Método que se encargará de actualizar registros
     *
     * @param usuario que será actualizado en el registro de la tabla
     * @return número de registros alterados
     * @throws SQLException propagamos la posible excepción
     */
    public int actualizar(Usuario usuario) throws SQLException {
        Connection conexion = null;
        PreparedStatement sentencia = null;
        int registros = 0;

        try {
            //evaluando como se hará la conexión a la base de datos
            conexion = (this.conexion != null) ? this.conexion : Conexion.getConnection();
            sentencia = conexion.prepareStatement(SQL_UPDATE);
            sentencia.setString(1, usuario.getUsuario());
            sentencia.setString(2, usuario.getContrasenia());
            sentencia.setInt(3, usuario.getId());
            registros = sentencia.executeUpdate();

        } finally {
            // cerramos los objetos
            Conexion.close(sentencia);
            //si la conexión se hizo por el método estático getConnection de Conexion
            if (this.conexion == null) {
                Conexion.close(conexion);
            }
        }

        return registros;
    }

    /**
     * Métod que se encargará de eliminar un registro
     *
     * @param usuario que será eliminado
     * @return número de registros alterados
     * @throws SQLException propagamos la posible excepción
     */
    public int eliminar(Usuario usuario) throws SQLException {
        Connection conexion = null;
        PreparedStatement sentencia = null;
        int registros = 0;

        try {
            //evaluando como se hará la conexión a la base de datos
            conexion = (this.conexion != null) ? this.conexion : Conexion.getConnection();
            sentencia = conexion.prepareStatement(SQL_DELETE);
            sentencia.setInt(1, usuario.getId());
            registros = sentencia.executeUpdate();

        } finally {
            // cerramos los objetos
            Conexion.close(sentencia);
            //si la conexión se hizo por el método estático getConnection de Conexion
            if (this.conexion == null) {
                Conexion.close(conexion);
            }
        }

        return registros;
    }
}
