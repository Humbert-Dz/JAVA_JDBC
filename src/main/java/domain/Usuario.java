package domain;

/**
 * Segunda clase de dominio
 *
 * @author BETO
 */
public class Usuario {

    //atributos
    private int id;
    private String usuario;
    private String contrasenia;

    //Sobrecarga de constructor
    /**
     * Constructor vacío
     */
    public Usuario() {
    }

    /**
     * Constructor con dos parametros
     *
     * @param usuario nombre de usuario
     * @param contrasenia de usuario
     */
    public Usuario(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    /**
     * Constructor parametrizado
     *
     * @param id de usuario
     * @param usuario nombre de usuario
     * @param contrasenia de usuario
     */
    public Usuario(int id, String usuario, String contrasenia) {
        this.id = id;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    //Métodos de acceso
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * sobreescritura de método
     *
     * @return representación en cadena de atributos del objeto this
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario{");
        sb.append("id: ").append(id);
        sb.append(", usuario: ").append(usuario);
        sb.append(", contrasenia: ").append(contrasenia);
        sb.append('}');
        return sb.toString();
    }

}
