
package org.utl.idgs.model;

/**
 *
 * @author Alda
 */
public class Area {
    private int AreaId;
    private String Nombre;
    private int Estatus;

    public Area(int AreaId, String Nombre, int Estatus) {
        this.AreaId = AreaId;
        this.Nombre = Nombre;
        this.Estatus = Estatus;
    }

    public Area() {
    }

    public int getAreaId() {
        return AreaId;
    }

    public void setAreaId(int AreaId) {
        this.AreaId = AreaId;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getEstatus() {
        return Estatus;
    }

    public void setEstatus(int Estatus) {
        this.Estatus = Estatus;
    }
}
