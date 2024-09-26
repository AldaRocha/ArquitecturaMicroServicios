
package org.utl.idgs.model;

/**
 *
 * @author Alda
 */
public class Tipousuario {
    private int TipoUsuarioId;
    private String Nombre;
    private int Activo;
    private String Expresion;
    private String ComentarioExpresion;

    public Tipousuario(int TipoUsuarioId, String Nombre, int Activo, String Expresion, String ComentarioExpresion) {
        this.TipoUsuarioId = TipoUsuarioId;
        this.Nombre = Nombre;
        this.Activo = Activo;
        this.Expresion = Expresion;
        this.ComentarioExpresion = ComentarioExpresion;
    }

    public Tipousuario() {
    }

    public int getTipoUsuarioId() {
        return TipoUsuarioId;
    }

    public void setTipoUsuarioId(int TipoUsuarioId) {
        this.TipoUsuarioId = TipoUsuarioId;
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

    public String getExpresion() {
        return Expresion;
    }

    public void setExpresion(String Expresion) {
        this.Expresion = Expresion;
    }

    public String getComentarioExpresion() {
        return ComentarioExpresion;
    }

    public void setComentarioExpresion(String ComentarioExpresion) {
        this.ComentarioExpresion = ComentarioExpresion;
    }
}
