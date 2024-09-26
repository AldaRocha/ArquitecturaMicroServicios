
package org.utl.idgs.model;

/**
 *
 * @author Alda
 */
public class Usuarioporperfil {
    private int UsuarioPorPerfilId;
    private Usuario Usuario;
    private Perfil Perfil;

    public Usuarioporperfil(int UsuarioPorPerfilId, Usuario Usuario, Perfil Perfil) {
        this.UsuarioPorPerfilId = UsuarioPorPerfilId;
        this.Usuario = Usuario;
        this.Perfil = Perfil;
    }

    public Usuarioporperfil() {
    }

    public int getUsuarioPorPerfilId() {
        return UsuarioPorPerfilId;
    }

    public void setUsuarioPorPerfilId(int UsuarioPorPerfilId) {
        this.UsuarioPorPerfilId = UsuarioPorPerfilId;
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }

    public Perfil getPerfil() {
        return Perfil;
    }

    public void setPerfil(Perfil Perfil) {
        this.Perfil = Perfil;
    }
}
