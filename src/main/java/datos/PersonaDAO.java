package datos;

import domain.Persona;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static datos.Conexion.*;

/**
 *
 * Clase DAO (Data Access Object) de persona
 *
 * Esta es una clase que se encarga de las operaciones básicas de persona en la
 * base de datos: * Create - Insert *Update *Delete
 *
 * @author BETO
 */
public class PersonaDAO {

    /**
     * Esta propiedad servirá para tener una conexión activa a la base de datos
     * ya que anteriormente en cada método (seleccionar, insertar, actualizar,
     * eliminar) se abre y se cierra una conexión con la DB, para trabajar con
     * transacciones debemos mantener una conexión siempre activa, ya que
     * propiamente la transaccion permite ejecutar varias sentencias en grupo
     * (como insertar, actualizar y eliminar)
     */
    private Connection conexionTransaccional;

    //constante que contiene sentencia para seleccionar todos los registros de la tabla
    private static final String SQL_SELECT = "SELECT * FROM persona";

    //costante que contiene sentencia para insertar un nuevo registro
    private static final String SQL_INSERT = "INSERT INTO persona (nombre, apellido, email, telefono) VALUES(?, ?, ?, ?)";

    //constante que contiene sentencia para actualizar un registro
    private static final String SQL_UPDATE = "UPDATE persona SET nombre = ?, apellido = ?, email = ?, telefono = ? WHERE id = ?";

    //constante que contiene sentencia para eliminar un registro
    private static final String SQL_DELETE = "DELETE FROM persona WHERE ID = ?";

    //Sobrecarga de constructores
    /**
     * Constructor vacío
     */
    public PersonaDAO() {
    }

    /**
     * Constructor con parámetro que recibe una conexion a la base de datos
     *
     * @param conexionTransaccional conexion a la base de datos
     */
    public PersonaDAO(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    /**
     * Método para realizar un select
     *
     * @return Lista de tipo personas
     */
    public List<Persona> seleccionar() throws SQLException {
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        Persona persona = null;
        List<Persona> personas = new ArrayList<>();

        try {
            //Asignamos el atributo de tipo conexion en caso de que no sea nulo, si es nulo se asignará una conexion a través del método estático getConnection
            conexion = (this.conexionTransaccional != null) ? this.conexionTransaccional : Conexion.getConnection();
            sentencia = conexion.prepareStatement(SQL_SELECT);
            resultado = sentencia.executeQuery();

            while (resultado.next()) {
                //recuperando resultado de la consulta
                int idPersona = resultado.getInt("id");
                String nombre = resultado.getString("nombre");
                String apellido = resultado.getString("apellido");
                String email = resultado.getString("email");
                String telefono = resultado.getString("telefono");

                persona = new Persona(idPersona, nombre, apellido, email, telefono);

                personas.add(persona);
            }

        } finally {
            Conexion.close(resultado);
            Conexion.close(sentencia);

            /**
             * si la conexión de la DB se recibió en el contructor y se realizó
             * correctamente esto no se ejecuta, sin embargo, la conexión falló
             * (usando el contructor con el parámetro) o el obj de PersonaDAO de
             * hizo con el constructor vacío, la conexión se hizo a través del
             * método getConnection() de la clase Conexion por lo que no es una
             * conexion estable / activa siempre. por ello se cierra (da igual
             * si se cierra ya que al no ser una conexion estable, esta nace y
             * muere cuando se ejecuta el método seleccionar)
             *
             */
            if (this.conexionTransaccional == null) {
                Conexion.close(conexion);
            }
        }

        return personas;
    }

    /**
     * Método para insertar un nuevo registro
     *
     * @param persona persona a insertar
     * @return numero registros afectados
     */
    public int insertar(Persona persona) throws SQLException {
        Connection conexion = null;
        PreparedStatement sentencia = null;
        int registros = 0;

        try {
            //Asignamos el atributo de tipo conexion en caso de que no sea nulo, si es nulo se asignará una conexion a través del método estático getConnection
            conexion = (this.conexionTransaccional != null) ? this.conexionTransaccional : Conexion.getConnection();
            sentencia = conexion.prepareStatement(SQL_INSERT);
            sentencia.setString(1, persona.getNombre());
            sentencia.setString(2, persona.getApellido());
            sentencia.setString(3, persona.getEmail());
            sentencia.setString(4, persona.getTelefono());
            registros = sentencia.executeUpdate();

        } finally {
            Conexion.close(sentencia);
            if (this.conexionTransaccional == null) {
                Conexion.close(conexion);
            }
        }

        return registros;
    }

    /**
     * Método para actualizar un registro
     *
     * @param persona registro a actualizar
     * @return numero registros afectados
     */
    public int actualizar(Persona persona) throws SQLException {
        Connection conexion = null;
        PreparedStatement sentencia = null;
        int registros = 0;

        try {
            //Asignamos el atributo de tipo conexion en caso de que no sea nulo, si es nulo se asignará una conexion a través del método estático getConnection
            conexion = (this.conexionTransaccional != null) ? this.conexionTransaccional : Conexion.getConnection();
            sentencia = conexion.prepareStatement(SQL_UPDATE);
            sentencia.setString(1, persona.getNombre());
            sentencia.setString(2, persona.getApellido());
            sentencia.setString(3, persona.getEmail());
            sentencia.setString(4, persona.getTelefono());
            sentencia.setInt(5, persona.getIdPersona());
            registros = sentencia.executeUpdate();

        } finally {
            Conexion.close(sentencia);
            if (this.conexionTransaccional == null) {
                Conexion.close(conexion);
            }
        }

        return registros;
    }

    /**
     * Método para eliminar un registro
     *
     * @param persona registro a eliminar
     * @return numero de registros afectados
     */
    public int eliminar(Persona persona) throws SQLException {
        Connection conexion = null;
        PreparedStatement sentencia = null;
        int registros = 0;

        try {
            //Asignamos el atributo de tipo conexion en caso de que no sea nulo, si es nulo se asignará una conexion a través del método estático getConnection
            conexion = this.conexionTransaccional != null ? this.conexionTransaccional : Conexion.getConnection();
            sentencia = conexion.prepareStatement(SQL_DELETE);
            sentencia.setInt(1, persona.getIdPersona());
            registros = sentencia.executeUpdate();

        } finally {
            Conexion.close(sentencia);
            if (this.conexionTransaccional == null) {
                Conexion.close(conexion);
            }
        }

        return registros;
    }

}
