package Modelo;

public class Cliente {
    private String documento;
    private String nombre;
    private String telefono;
    private String razonSocial;

    public Cliente(String documento, String nombre, String telefono, String razonSocial) {
        this.documento = documento;
        this.nombre = nombre;
        this.telefono = telefono;
        this.razonSocial = razonSocial;
    }

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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

 
}
