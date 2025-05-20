package Modelo;

public class Cliente {
    private String documento;
    private String nombre;
    private String apellido;
    private String telefono;
    private String correo;
    private int idPersona;

    // Constructor para consultas JOIN que traen nombre y apellido desde persona
    public Cliente(String documento, String nombre, String apellido, String telefono, String correo) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
    }

    // Constructor para registrar cliente (requiere también nombre y apellido para registrar en persona)
    public Cliente(String documento, String nombre, String apellido, String telefono, String correo, int idPersona) {
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.idPersona = idPersona;
    }

    // Constructor alternativo cuando ya se tiene el idPersona (evita doble inserción)
    public Cliente(String documento, String telefono, String correo, int idPersona) {
        this.documento = documento;
        this.telefono = telefono;
        this.correo = correo;
        this.idPersona = idPersona;
    }

    // Getters y Setters

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }
}
