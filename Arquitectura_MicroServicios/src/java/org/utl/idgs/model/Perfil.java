
package org.utl.idgs.model;

/**
 *
 * @author Alda
 */
public class Perfil {
    private int PerfilId;
    private String Nombre;
    private int Activo;
    private int TiempoSesion;
    private int TiempoInactividad;
    private int SesionesSimultaneas;

    public Perfil(int PerfilId, String Nombre, int Activo, int TiempoSesion, int TiempoInactividad, int SesionesSimultaneas) {
        this.PerfilId = PerfilId;
        this.Nombre = Nombre;
        this.Activo = Activo;
        this.TiempoSesion = TiempoSesion;
        this.TiempoInactividad = TiempoInactividad;
        this.SesionesSimultaneas = SesionesSimultaneas;
    }

    public Perfil() {
    }

    public int getPerfilId() {
        return PerfilId;
    }

    public void setPerfilId(int PerfilId) {
        this.PerfilId = PerfilId;
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

    public int getTiempoSesion() {
        return TiempoSesion;
    }

    public void setTiempoSesion(int TiempoSesion) {
        this.TiempoSesion = TiempoSesion;
    }

    public int getTiempoInactividad() {
        return TiempoInactividad;
    }

    public void setTiempoInactividad(int TiempoInactividad) {
        this.TiempoInactividad = TiempoInactividad;
    }

    public int getSesionesSimultaneas() {
        return SesionesSimultaneas;
    }

    public void setSesionesSimultaneas(int SesionesSimultaneas) {
        this.SesionesSimultaneas = SesionesSimultaneas;
    }
}
