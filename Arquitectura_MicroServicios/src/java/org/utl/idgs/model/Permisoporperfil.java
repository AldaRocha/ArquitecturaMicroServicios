
package org.utl.idgs.model;

/**
 *
 * @author Alda
 */
public class Permisoporperfil {
    private int PermisoPorPerfilId;
    private Permisoporpantalla PermisoPorPantalla;
    private Perfil Perfil;

    public Permisoporperfil(int PermisoPorPerfilId, Permisoporpantalla PermisoPorPantalla, Perfil Perfil) {
        this.PermisoPorPerfilId = PermisoPorPerfilId;
        this.PermisoPorPantalla = PermisoPorPantalla;
        this.Perfil = Perfil;
    }

    public Permisoporperfil() {
    }

    public int getPermisoPorPerfilId() {
        return PermisoPorPerfilId;
    }

    public void setPermisoPorPerfilId(int PermisoPorPerfilId) {
        this.PermisoPorPerfilId = PermisoPorPerfilId;
    }

    public Permisoporpantalla getPermisoPorPantalla() {
        return PermisoPorPantalla;
    }

    public void setPermisoPorPantalla(Permisoporpantalla PermisoPorPantalla) {
        this.PermisoPorPantalla = PermisoPorPantalla;
    }

    public Perfil getPerfil() {
        return Perfil;
    }

    public void setPerfil(Perfil Perfil) {
        this.Perfil = Perfil;
    }
}
