package Modelo;

public class Producto {

    int Codigo;
    String Descripcion;
    int Precio;
    int Cantidad;
    int Material;   // ahora es int
    MarcaItem Marca;      // ahora es int
    String Nombre;
    int Color;

    public Producto() {
        this.Codigo = 0;
        this.Descripcion = "";
        this.Precio = 0;
        this.Cantidad = 0;
        this.Material = 0;
        this.Marca = null;
        this.Nombre = "";
        this.Color = 0;
    }// ya era int

    public Producto(int Codigo, String Descripcion, int Precio, int Cantidad, int Material, MarcaItem Marca, String Nombre, int Color) {
        this.Codigo = Codigo;
        this.Descripcion = Descripcion;
        this.Precio = Precio;
        this.Cantidad = Cantidad;
        this.Material = Material;
        this.Marca = Marca;
        this.Nombre = Nombre;
        this.Color = Color;
    }

    public Producto(int Codigo, String Descripcion, int Precio, int Cantidad, int Material, int Marca, String Nombre, int Color) {
        this.Codigo = Codigo;
        this.Descripcion = Descripcion;
        this.Precio = Precio;
        this.Cantidad = Cantidad;
        this.Material = Material;
        this.Marca.setIdMarca(Marca);
        this.Nombre = Nombre;
        this.Color = Color;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int Precio) {
        this.Precio = Precio;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public int getMaterial() {
        return Material;
    }

    public void setMaterial(int Material) {
        this.Material = Material;
    }

    public MarcaItem getMarca() {
        return Marca;
    }

    public void setMarca(MarcaItem Marca) {
        this.Marca = Marca;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getColor() {
        return Color;
    }

    public void setColor(int Color) {
        this.Color = Color;
    }
}
