
package org.utl.idgs.model;

/**
 *
 * @author Alda
 */
public class Persona {
    private int PersonaId;
    private String Nombre;
    private String Email;
    private String Telefono;
    private String Curp;
    private int NumeroEmpleado;
    private String TipoContrato;
    private String FechaIngreso;
    private String Grupo;
    private int Estatus;
    private Area Area;

    public Persona(int PersonaId, String Nombre, String Email, String Telefono, String Curp, int NumeroEmpleado, String TipoContrato, String FechaIngreso, String Grupo, int Estatus, Area Area) {
        this.PersonaId = PersonaId;
        this.Nombre = Nombre;
        this.Email = Email;
        this.Telefono = Telefono;
        this.Curp = Curp;
        this.NumeroEmpleado = NumeroEmpleado;
        this.TipoContrato = TipoContrato;
        this.FechaIngreso = FechaIngreso;
        this.Grupo = Grupo;
        this.Estatus = Estatus;
        this.Area = Area;
    }

    public Persona() {
    }

    public int getPersonaId() {
        return PersonaId;
    }

    public void setPersonaId(int PersonaId) {
        this.PersonaId = PersonaId;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getCurp() {
        return Curp;
    }

    public void setCurp(String Curp) {
        this.Curp = Curp;
    }

    public int getNumeroEmpleado() {
        return NumeroEmpleado;
    }

    public void setNumeroEmpleado(int NumeroEmpleado) {
        this.NumeroEmpleado = NumeroEmpleado;
    }

    public String getTipoContrato() {
        return TipoContrato;
    }

    public void setTipoContrato(String TipoContrato) {
        this.TipoContrato = TipoContrato;
    }

    public String getFechaIngreso() {
        return FechaIngreso;
    }

    public void setFechaIngreso(String FechaIngreso) {
        this.FechaIngreso = FechaIngreso;
    }

    public String getGrupo() {
        return Grupo;
    }

    public void setGrupo(String Grupo) {
        this.Grupo = Grupo;
    }

    public int getEstatus() {
        return Estatus;
    }

    public void setEstatus(int Estatus) {
        this.Estatus = Estatus;
    }

    public Area getArea() {
        return Area;
    }

    public void setArea(Area Area) {
        this.Area = Area;
    }
}
