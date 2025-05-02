package Modelo;

public class Producto {

    int Codigo;
    String Descripcion;
    int Precio;
    int Cantidad;
    MaterialItem Material;   // ahora es int
    MarcaItem Marca;      // ahora es int
    String Nombre;
    ColorItem Color;
//tengo sueño
    public Producto() {
        this.Codigo = 0;
        this.Descripcion = "";
        this.Precio = 0;
        this.Cantidad = 0;
        this.Material = null;
        this.Marca = null;
        this.Nombre = "";
        this.Color = null;
    }// ya era int

    public Producto(int Codigo, String Descripcion, int Precio, int Cantidad, MaterialItem Material, MarcaItem Marca, String Nombre, ColorItem Color) {
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
    this.Material = new MaterialItem(Material, "");  // Puedes usar "" si el nombre no se conoce
    this.Marca = new MarcaItem(Marca, "");
    this.Nombre = Nombre;
    this.Color = new ColorItem(Color, "");
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

    public MaterialItem getMaterial() {
        return Material;
    }

    public void setMaterial(MaterialItem Material) {
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

    public ColorItem getColor() {
        return Color;
    }

    public void setColor(ColorItem Color) {
        this.Color = Color;
    }
}
