
package org.utl.idgs.model;

/**
 *
 * @author Alda
 */
public class Permisoporpantalla {
    private int PermisoporpantallaId;
    private Permiso Permiso;
    private Pantalla Pantalla;

    public Permisoporpantalla(int PermisoporpantallaId, Permiso Permiso, Pantalla Pantalla) {
        this.PermisoporpantallaId = PermisoporpantallaId;
        this.Permiso = Permiso;
        this.Pantalla = Pantalla;
    }

    public Permisoporpantalla() {
    }

    public int getPermisoporpantallaId() {
        return PermisoporpantallaId;
    }

    public void setPermisoporpantallaId(int PermisoporpantallaId) {
        this.PermisoporpantallaId = PermisoporpantallaId;
    }

    public Permiso getPermiso() {
        return Permiso;
    }

    public void setPermiso(Permiso Permiso) {
        this.Permiso = Permiso;
    }

    public Pantalla getPantalla() {
        return Pantalla;
    }

    public void setPantalla(Pantalla Pantalla) {
        this.Pantalla = Pantalla;
    }
}
