package test;

import datos.PersonaDAO;
import domain.Persona;
import java.util.List;

/**
 *
 * Clase main
 * 
 * @author BETO
 */
public class TestManejoPersonas {

    public static void main(String[] args) {
        //objeto nuevo
        PersonaDAO personaDAO = new PersonaDAO();

        imprimir(personaDAO);
        
        //insertando un nuevo objeto de tipo persona
        //Persona persona = new Persona("Josue", "Alarcon", "josue@.com", "311819891");
        //personaDAO.insertar(persona);

        //actualizando un registro
        //Persona persona2 = new Persona(1 ,"Perro", "Alarcon", "josue@.com", "311819891");
        //personaDAO.actualizar(persona2);

        //eliminando un registro
        Persona persona3 = new Persona(1 ,"Perro", "Alarcon", "josue@.com", "311819891");
        PersonaDAO.eliminar(persona3);
        
        
        
        imprimir(personaDAO);

    }

    public static void imprimir(PersonaDAO personaDAO) {
        List<Persona> personas = personaDAO.seleccionar();
        personas.forEach(persona -> {
            System.out.println(persona);
        });
        System.out.println("");
    }

}
