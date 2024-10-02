
package org.utl.idgs.model;

import java.util.Date;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Alda
 */
public class Usuario {
    private int UsuarioId;
    private Persona Persona;
    private Tipousuario TipoUsuario;
    private String Cuenta;
    private String Contrasena;
    private String Email;
    private int ReiniciarContrasena;
    private int Activo;
    private String LastToken;
    private String DateLastToken;
    
    private int PerfilId;

    public Usuario(int UsuarioId, Persona Persona, Tipousuario TipoUsuario, String Cuenta, String Contrasena, String Email, int ReiniciarContrasena, int Activo, String LastToken, String DateLastToken, int PerfilId) {
        this.UsuarioId = UsuarioId;
        this.Persona = Persona;
        this.TipoUsuario = TipoUsuario;
        this.Cuenta = Cuenta;
        this.Contrasena = Contrasena;
        this.Email = Email;
        this.ReiniciarContrasena = ReiniciarContrasena;
        this.Activo = Activo;
        this.LastToken = LastToken;
        this.DateLastToken = DateLastToken;
        this.PerfilId = PerfilId;
    }

    public Usuario() {
    }

    public int getPerfilId() {
        return PerfilId;
    }

    public void setPerfilId(int PerfilId) {
        this.PerfilId = PerfilId;
    }

    public int getUsuarioId() {
        return UsuarioId;
    }

    public void setUsuarioId(int UsuarioId) {
        this.UsuarioId = UsuarioId;
    }

    public Persona getPersona() {
        return Persona;
    }

    public void setPersona(Persona Persona) {
        this.Persona = Persona;
    }

    public Tipousuario getTipoUsuario() {
        return TipoUsuario;
    }

    public void setTipoUsuario(Tipousuario TipoUsuario) {
        this.TipoUsuario = TipoUsuario;
    }

    public String getCuenta() {
        return Cuenta;
    }

    public void setCuenta(String Cuenta) {
        this.Cuenta = Cuenta;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public int getReiniciarContrasena() {
        return ReiniciarContrasena;
    }

    public void setReiniciarContrasena(int ReiniciarContrasena) {
        this.ReiniciarContrasena = ReiniciarContrasena;
    }

    public int getActivo() {
        return Activo;
    }

    public void setActivo(int Activo) {
        this.Activo = Activo;
    }

    public String getLastToken() {
        return LastToken;
    }

    public void setLastToken(String LastToken) {
        this.LastToken = LastToken;
    }

    public String getDateLastToken() {
        return DateLastToken;
    }

    public void setDateLastToken(String DateLastToken) {
        this.DateLastToken = DateLastToken;
    }
    
    public void setLastToken(){
        String u=this.getCuenta();
        String p=this.getContrasena();
        String k=new Date().toString();
        String x=(DigestUtils.sha256Hex(u+";"+p+";"+k));
        this.LastToken=x;
    }
    
    public void setDateLastToken(){
        String fecha=new Date().toString();
        this.DateLastToken=fecha;
    }
}
