
package org.utl.idgs.model;

/**
 *
 * @author Alda
 */
public class Modulo {
    private int ModuloId;
    private String Identificador;
    private String Nombre;
    private int Activo;
    private String Icono;

    public Modulo(int ModuloId, String Identificador, String Nombre, int Activo, String Icono) {
        this.ModuloId = ModuloId;
        this.Identificador = Identificador;
        this.Nombre = Nombre;
        this.Activo = Activo;
        this.Icono = Icono;
    }

    public Modulo() {
    }

    public int getModuloId() {
        return ModuloId;
    }

    public void setModuloId(int ModuloId) {
        this.ModuloId = ModuloId;
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

    public String getIcono() {
        return Icono;
    }

    public void setIcono(String Icono) {
        this.Icono = Icono;
    }
}
