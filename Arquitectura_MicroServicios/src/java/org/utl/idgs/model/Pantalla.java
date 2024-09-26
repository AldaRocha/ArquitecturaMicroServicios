
package org.utl.idgs.model;

/**
 *
 * @author Alda
 */
public class Pantalla {
    private int PantallaId;
    private Modulo Modulo;
    private String Identificador;
    private String Nombre;
    private int Activo;
    private String Ruta;
    private String Icono;

    public Pantalla(int PantallaId, Modulo Modulo, String Identificador, String Nombre, int Activo, String Ruta, String Icono) {
        this.PantallaId = PantallaId;
        this.Modulo = Modulo;
        this.Identificador = Identificador;
        this.Nombre = Nombre;
        this.Activo = Activo;
        this.Ruta = Ruta;
        this.Icono = Icono;
    }

    public Pantalla() {
    }

    public int getPantallaId() {
        return PantallaId;
    }

    public void setPantallaId(int PantallaId) {
        this.PantallaId = PantallaId;
    }

    public Modulo getModulo() {
        return Modulo;
    }

    public void setModulo(Modulo Modulo) {
        this.Modulo = Modulo;
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

    public String getRuta() {
        return Ruta;
    }

    public void setRuta(String Ruta) {
        this.Ruta = Ruta;
    }

    public String getIcono() {
        return Icono;
    }

    public void setIcono(String Icono) {
        this.Icono = Icono;
    }
}
