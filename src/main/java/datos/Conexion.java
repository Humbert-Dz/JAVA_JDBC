/*
 *El nombre del paquete hace referencia a la capa de datos de la app
 */
package datos;

import java.sql.*;

/**
 *
 * Esta clase será la encargada de establecer una conexión con la base de datos
 * y cerrar los objetos que requerimos para ejecutar sentencias
 *
 * @author BETO
 */
public class Conexion {

    //constante que contiene la cadena de conexión a la DB
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";

    //constante con el usuario de mysql
    private static final String JDBC_USER = "beto";

    //constante con la contraseña del usuario de mysql
    private static final String JDBC_PASSWORD = "h13h12h2002";

    /**
     * Método que retorna una conexión a la DB, propaga posible excepcion
     *
     * @return conexión a DB
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    //Sobrecarga de método close
    
    /**
     * Método para cerrar el objeto que representa el resultado de la consulta
     * en una tabla
     *
     * @param resultado referencia al objeto a cerrar
     */
    public static void close(ResultSet resultado) {
        try {
            resultado.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    /**
     * Método para cerrar el objeto por el cual ejecutamos nuestras sentencias
     * SQL
     *
     * @param sentencia que referencia al objeto a cerrar
     */
    public static void close(Statement sentencia) {
        try {
            sentencia.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    /**
     * Método para cerrar el objeto por el cual ejecutamos nuestras sentencias
     * SQL, es exactamente igual al método anterior pero el tipo
     * PreparedStatement es más maduro, por lo que se trabajará la mayor parte
     * del tiempo con el tipo PreparedStatement
     *
     * @param sentencia que referencia al objeto a cerrar
     */
    public static void close(PreparedStatement sentencia) {
        try {
            sentencia.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    /**
     * Método para cerrar el objeto por el cual hacemos la conexión a la DB, es
     * decir este método cerrará la conexión a la DB
     *
     * @param conexion referencia al objeto a cerrar
     */
    public static void close(Connection conexion) {
        try {
            conexion.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

}
