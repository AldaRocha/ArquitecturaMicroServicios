
package org.utl.idgs.model;

/**
 *
 * @author Alda
 */
public class Permiso {
    private int PermisoId;
    private String Identificador;
    private String Nombre;
    private int Activo;
    private int Tipo;

    public Permiso(int PermisoId, String Identificador, String Nombre, int Activo, int Tipo) {
        this.PermisoId = PermisoId;
        this.Identificador = Identificador;
        this.Nombre = Nombre;
        this.Activo = Activo;
        this.Tipo = Tipo;
    }

    public Permiso() {
    }

    public int getPermisoId() {
        return PermisoId;
    }

    public void setPermisoId(int PermisoId) {
        this.PermisoId = PermisoId;
    }

    public String getIdentificador() {
        return Identificador;
    }

    public void setIdentificador(String Identificador) {
        this.Identificador = Identificador;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getActivo() {
        return Activo;
    }

    public void setActivo(int Activo) {
        this.Activo = Activo;
    }

    public int getTipo() {
        return Tipo;
    }

    public void setTipo(int Tipo) {
        this.Tipo = Tipo;
    }
}
