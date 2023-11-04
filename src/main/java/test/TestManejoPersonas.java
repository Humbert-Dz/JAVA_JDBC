package test;

import datos.Conexion;
import datos.PersonaDAO;
import domain.Persona;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Clase main
 *
 * @author BETO
 */
public class TestManejoPersonas {

    public static void main(String[] args) {

        Connection conexion = null;

        try {
            conexion = Conexion.getConnection();

            //verificamos si la conexion está en autocommit para desactivarlo
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }

            //objeto nuevo de tipo PersonaDAO
            PersonaDAO personaDAO = new PersonaDAO(conexion);

            imprimir(personaDAO);

            /**
             * *Iniciando transacción registro a modificar:
             *
             */
            //Persona persona = new Persona(5, "Macario Antonio", "Zacarias Ruiz", "macarioAntorio.com", "311819891");
            Persona persona = new Persona(5, "Jose Manuel", "Sánchez Artimiliano", "josemanuel.com", "311819891");
            //Actualizar
            personaDAO.actualizar(persona);

            //inserción
            Persona personaInsert = new Persona("Juan Diego", "Pérez León", "Lostigresdelnorte.com", "17261819");
            personaDAO.insertar(personaInsert);

            //confirmamos los cambios
            conexion.commit();
            System.out.println("Se ha hecho commit de la transaccion");

            imprimir(personaDAO);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Entramos a rollback");
            //Hacemos rollback de los cambios
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex.printStackTrace(System.out);
            }
        }
    }

    public static void imprimir(PersonaDAO personaDAO) throws SQLException {
        List<Persona> personas = personaDAO.seleccionar();
        personas.forEach(persona -> {
            System.out.println(persona);
        });
        System.out.println("");
    }

}
