
package org.utl.idgs.core;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.utl.idgs.connection.ConexionMySQL;
import org.utl.idgs.model.Persona;
import org.utl.idgs.model.Tipousuario;
import org.utl.idgs.model.Usuario;

/**
 *
 * @author Alda
 */
public class ControllerUsuario {
    public List<Usuario> getAllUsuario() throws Exception{
        String sql = "SELECT * FROM v_usuarios";
        
        ConexionMySQL connMySQL=new ConexionMySQL();
        
        Connection conn = connMySQL.open();
        
        Statement stmt = (Statement)conn.createStatement();
        
        ResultSet rs = stmt.executeQuery(sql);
        
        List<Usuario> lstUsuario = new ArrayList<>();
        while(rs.next())
            lstUsuario.add(fill(rs));
        
        rs.close();
        stmt.close();
        conn.close();
        connMySQL.close();
        
        return lstUsuario;
    }
    
    private Usuario fill(ResultSet rs) throws Exception{
        Persona p = new Persona();
        Tipousuario tu = new Tipousuario();
        Usuario u = new Usuario();
        
        tu.setTipoUsuarioId(rs.getInt("TipoUsuarioId"));
        
        p.setPersonaId(rs.getInt("PersonaId"));
        p.setNombre(rs.getString("Nombre"));
        
        u.setUsuarioId(rs.getInt("UsuarioId"));
        u.setPersona(p);
        u.setTipoUsuario(tu);
        u.setCuenta(rs.getString("Cuenta"));
        u.setEmail(rs.getString("Email"));
        u.setActivo(rs.getInt("Activo"));
        u.setPerfilId(rs.getInt("PerfilId"));
        
        return u;
    }
    
    public int guardarUsuario(Usuario u) throws Exception{
        String sql = "{CALL guardarUsuario(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        
        int UsuarioIdGenerado = -1;
        
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        Connection conn = connMySQL.open();
        
        CallableStatement clblsmt = conn.prepareCall(sql);
        
        clblsmt.setInt(1, u.getPersona().getPersonaId());
        clblsmt.setInt(2, u.getTipoUsuario().getTipoUsuarioId());
        clblsmt.setString(3, u.getCuenta());
        clblsmt.setString(4, u.getContrasena());
        clblsmt.setString(5, u.getEmail());
        clblsmt.setInt(6, u.getActivo());
        clblsmt.setInt(7, u.getPerfilId());
        
        clblsmt.registerOutParameter(8, Types.INTEGER);
        clblsmt.registerOutParameter(9, Types.INTEGER);
        
        clblsmt.executeUpdate();
        
        UsuarioIdGenerado = clblsmt.getInt(9);
        
        u.setUsuarioId(UsuarioIdGenerado);
        
        clblsmt.close();
        conn.close();
        connMySQL.close();
        
        return UsuarioIdGenerado;
    }
    
    public void actualizarUsuario(Usuario u) throws Exception{
        String sql = "{CALL actualizarUsuario(?, ?, ?, ?, ?, ?, ?)}";
        
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        Connection conn = connMySQL.open();
        
        CallableStatement clblsmt = conn.prepareCall(sql);
        
        clblsmt.setInt(1, u.getUsuarioId());
        clblsmt.setInt(2, u.getPersona().getPersonaId());
        clblsmt.setInt(3, u.getTipoUsuario().getTipoUsuarioId());
        clblsmt.setString(4, u.getCuenta());
        clblsmt.setString(5, u.getEmail());
        clblsmt.setInt(6, u.getActivo());
        clblsmt.setInt(7, u.getPerfilId());
        
        clblsmt.executeUpdate();
        
        clblsmt.close();
        conn.close();
        connMySQL.close();
    }
    
    public boolean eliminarUsuario(int UsuarioId) throws Exception{
        boolean respuesta = false;
        String sql1 = "DELETE FROM usuarioporperfil WHERE UsuarioId = ?";
        String sql2 = "DELETE FROM usuario WHERE UsuarioId = ?";
        
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        Connection conn = connMySQL.open();
        
        PreparedStatement prepst1 = conn.prepareCall(sql1);
        PreparedStatement prepst2 = conn.prepareCall(sql2);
        
        prepst1.setInt(1, UsuarioId);
        prepst1.execute();
        
        prepst2.setInt(1, UsuarioId);
        prepst2.execute();
        
        respuesta = true;
        
        prepst1.close();
        prepst2.close();
        conn.close();
        connMySQL.close();
        
        return respuesta;
    }
    
    public boolean cambiarContraseña(Usuario u) throws Exception{
        boolean respuesta = false;
        String sql = "{CALL cambiarContraseña(?, ?)}";
        
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        Connection conn = connMySQL.open();
        
        CallableStatement clblsmt = conn.prepareCall(sql);
        
        clblsmt.setInt(1, u.getUsuarioId());
        clblsmt.setString(2, u.getContrasena());
        clblsmt.execute();
        respuesta = true;
        
        clblsmt.close();
        conn.close();
        connMySQL.close();
        
        return respuesta;
    }
}
