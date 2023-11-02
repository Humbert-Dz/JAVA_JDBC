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

    //constante que contiene sentencia para seleccionar todos los registros de la tabla
    private static final String SQL_SELECT = "SELECT * FROM persona";

    //costante que contiene sentencia para insertar un nuevo registro
    private static final String SQL_INSERT = "INSERT INTO persona (nombre, apellido, email, telefono) VALUES(?, ?, ?, ?)";

    //constante que contiene sentencia para actualizar un registro
    private static final String SQL_UPDATE = "UPDATE persona SET nombre = ?, apellido = ?, email = ?, telefono = ? WHERE id = ?";

    //constante que contiene sentencia para eliminar un registro
    private static final String SQL_DELETE = "DELETE FROM persona WHERE ID = ?";

    /**
     * Método para realizar un select
     *
     * @return Lista de tipo personas
     */
    public List<Persona> seleccionar() {
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        Persona persona = null;
        List<Persona> personas = new ArrayList<>();

        try {
            conexion = Conexion.getConnection();
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

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(resultado);
            Conexion.close(sentencia);
            Conexion.close(conexion);
        }

        return personas;
    }

    /**
     * Método para insertar un nuevo registro
     *
     * @param persona persona a insertar
     * @return numero registros afectados
     */
    public int insertar(Persona persona) {
        Connection conexion = null;
        PreparedStatement sentencia = null;
        int registros = 0;

        try {
            conexion = Conexion.getConnection();
            sentencia = conexion.prepareStatement(SQL_INSERT);
            sentencia.setString(1, persona.getNombre());
            sentencia.setString(2, persona.getApellido());
            sentencia.setString(3, persona.getEmail());
            sentencia.setString(4, persona.getTelefono());
            registros = sentencia.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(sentencia);
            Conexion.close(conexion);
        }

        return registros;
    }

    /**
     * Método para actualizar un registro
     *
     * @param persona registro a actualizar
     * @return numero registros afectados
     */
    public int actualizar(Persona persona) {
        Connection conexion = null;
        PreparedStatement sentencia = null;
        int registros = 0;

        try {
            conexion = Conexion.getConnection();
            sentencia = conexion.prepareStatement(SQL_UPDATE);
            sentencia.setString(1, persona.getNombre());
            sentencia.setString(2, persona.getApellido());
            sentencia.setString(3, persona.getEmail());
            sentencia.setString(4, persona.getTelefono());
            sentencia.setInt(5, persona.getIdPersona());
            registros = sentencia.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(sentencia);
            Conexion.close(conexion);
        }

        return registros;
    }

    /**
     *
     * @param persona
     * @return
     */
    public static int eliminar(Persona persona) {
        Connection conexion = null;
        PreparedStatement sentencia = null;
        int registros = 0;

        try {
            conexion = Conexion.getConnection();
            sentencia = conexion.prepareStatement(SQL_DELETE);
            sentencia.setInt(1, persona.getIdPersona());
            registros = sentencia.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(sentencia);
            Conexion.close(conexion);
        }

        return registros;
    }

}
