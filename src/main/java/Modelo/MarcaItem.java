package Modelo;

import java.util.Objects;

public class MarcaItem {
    private int idMarca;
    private String nombre;
    
    public MarcaItem(int idMarca, String nombre){
        this.idMarca = idMarca;
        this.nombre = nombre;
    }
    
    public int getIdMarca() { 
        return idMarca; 
    }
    public String getNombre() { 
        return nombre; 
    }
    
    @Override
    public String toString() { return getNombre(); }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ( o == null || getClass() != o.getClass()) return false;
        MarcaItem marcaItem = (MarcaItem) o;
        return getIdMarca() == marcaItem.getIdMarca();
    }
    
    @Override
    public int hashCode() { return Objects.hash(getIdMarca()); }

    /**
     * @param idMarca the idMarca to set
     */
    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}