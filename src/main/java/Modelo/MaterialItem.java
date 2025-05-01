package Modelo;

import java.util.Objects;

public class MaterialItem {
    private int idMaterial;
    private String nombre;
    
    public MaterialItem(int idMaterial, String nombre){
        this.idMaterial = idMaterial;
        this.nombre = nombre;
    }
    
    public int getIdMaterial() { 
        return idMaterial; 
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
        MaterialItem MaterialItem = (MaterialItem) o;
        return getIdMaterial() == MaterialItem.getIdMaterial();
    }
    
    @Override
    public int hashCode() { return Objects.hash(getIdMaterial()); }

    /**
     * @param idMaterial the idMaterial to set
     */
    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}