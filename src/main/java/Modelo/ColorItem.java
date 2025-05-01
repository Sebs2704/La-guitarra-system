package Modelo;

import java.util.Objects;

public class ColorItem {
    private int idColor;
    private String nombre;
    
    public ColorItem(int idColor, String nombre){
        this.idColor = idColor;
        this.nombre = nombre;
    }
    
    public int getIdColor() { 
        return idColor; 
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
        ColorItem ColorItem = (ColorItem) o;
        return getIdColor() == ColorItem.getIdColor();
    }
    
    @Override
    public int hashCode() { return Objects.hash(getIdColor()); }

    /**
     * @param idColor the idColor to set
     */
    public void setIdColor(int idColor) {
        this.idColor = idColor;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}