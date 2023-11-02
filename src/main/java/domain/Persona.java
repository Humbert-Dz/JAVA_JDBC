package domain;

/**
 *
 * Clase de entidad / clase de dominio: Se llama así a las clases que tienen una
 * representación en la base de datos
 *
 * @author BETO
 */
public class Persona {

    //Atributos
    private int idPersona;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;

    //Sobrecarga de constructor
    /**
     * Constructor vacío
     */
    public Persona() {
    }

    /**
     * Constructor que podría servir para eliminar un registro
     *
     * @param idPersona id de la persona
     */
    public Persona(int idPersona) {
        this.idPersona = idPersona;
    }

    /**
     * Constructor parametrizado
     *
     * @param nombre de la persona
     * @param apellido de la persona
     * @param email de la persona
     * @param telefono de la persona
     */
    public Persona(String nombre, String apellido, String email, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }

    /**
     * Contructor parametrizado con todos los atributos
     *
     * @param idPersona de la persona
     * @param nombre de la persona
     * @param apellido de la persona
     * @param email de la persona
     * @param telefono de la persona
     */
    public Persona(int idPersona, String nombre, String apellido, String email, String telefono) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
    }

    //Métodos de acceso
    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Sobreescritura del método toString
     *
     * @return estado actual del objeto this
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Persona{");
        sb.append("idPersona=").append(idPersona);
        sb.append(", nombre=").append(nombre);
        sb.append(", apellido=").append(apellido);
        sb.append(", email=").append(email);
        sb.append(", telefono=").append(telefono);
        sb.append('}');
        return sb.toString();
    }

}
