package Modelo;


public class Usuario {
    private String Usuario;
    private String Contraseña;
    private String Nombre;
    private String Apellido;


    public Usuario(String Usuario1, String Contraseña1, String nombre, String Apellido1, String ID1) {
    }

    public Usuario(String Usuario, String Contraseña, String Nombre, String Apellido) {
        this.Usuario = Usuario;
        this.Contraseña = Contraseña;
        this.Nombre = Nombre;
        this.Apellido = Apellido;

    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    public String getApellido(){
        return Apellido;
    }
    
    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }
}
