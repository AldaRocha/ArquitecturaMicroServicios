
package org.utl.idgs.core;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.utl.idgs.connection.ConexionMySQL;
import org.utl.idgs.model.Area;
import org.utl.idgs.model.Modulo;
import org.utl.idgs.model.Pantalla;
import org.utl.idgs.model.Persona;
import org.utl.idgs.model.Tipousuario;
import org.utl.idgs.model.Usuario;

/**
 *
 * @author Alda
 */
public class ControllerLogin {
    public Usuario login(String cuenta, String contraseña) throws Exception{
        String sql = "SELECT * FROM v_empleadosLogin ve WHERE ve.Cuenta=? AND ve.Contrasena=?";
        
        ConexionMySQL connMySQL=new ConexionMySQL();
        
        Connection conn=connMySQL.open();
        
        PreparedStatement prepst=conn.prepareStatement(sql);
        
        ResultSet rs=null;
        
        prepst.setString(1, cuenta);
        prepst.setString(2, contraseña);
        
        rs=prepst.executeQuery();
        
        Usuario u = null;
        if (rs.next())
            u = (fill(rs));
        
        rs.close();
        prepst.close();
        connMySQL.close();
        
        return u;
    }
    
    private Usuario fill(ResultSet rs) throws Exception{
        Area a = new Area();
        Persona p = new Persona();
        Tipousuario tu = new Tipousuario();
        Usuario u = new Usuario();
        
        a.setAreaId(rs.getInt("AreaId"));
        a.setNombre(rs.getString("NombreArea"));
        a.setEstatus(rs.getInt("EstatusArea"));
        
        p.setPersonaId(rs.getInt("PersonaId"));
        p.setNombre(rs.getString("NombrePersona"));
        p.setEmail(rs.getString("EmailPersona"));
        p.setTelefono(rs.getString("Telefono"));
        p.setCurp(rs.getString("Curp"));
        p.setNumeroEmpleado(rs.getInt("NumeroEmpleado"));
        p.setTipoContrato(rs.getString("TipoContrato"));
        p.setFechaIngreso(rs.getString("FechaIngreso"));
        p.setGrupo(rs.getString("Grupo"));
        p.setEstatus(rs.getInt("EstatusPersona"));
        p.setArea(a);
        
        tu.setTipoUsuarioId(rs.getInt("TipoUsuarioId"));
        tu.setNombre(rs.getString("NombreTipoUsuario"));
        tu.setActivo(rs.getInt("ActivoTipoUsuario"));
        tu.setExpresion(rs.getString("Expresion"));
        tu.setComentarioExpresion(rs.getString("ComentarioExpresion"));
        
        u.setUsuarioId(rs.getInt("UsuarioId"));
        u.setPersona(p);
        u.setTipoUsuario(tu);
        u.setCuenta(rs.getString("Cuenta"));
        u.setContrasena(rs.getString("Contrasena"));
        u.setEmail(rs.getString("EmailUsuario"));
        u.setReiniciarContrasena(rs.getInt("ReiniciarContrasena"));
        u.setActivo(rs.getInt("ActivoUsuario"));
        u.setLastToken(rs.getString("LastToken"));
        u.setDateLastToken(rs.getString("DateLastToken"));
        
        return u;
    }
    
    public void generarToken(int UsuarioId, String Token) throws SQLException{       
        String sql="CALL generarToken(?, ?)";
        
        ConexionMySQL connMySQL=new ConexionMySQL();
        
        Connection conn=connMySQL.open();
        
        CallableStatement clblsmt=conn.prepareCall(sql);
        
        clblsmt.setInt(1, UsuarioId);
        clblsmt.setString(2, Token);
        
        clblsmt.executeUpdate();
        
        clblsmt.close();
        connMySQL.close();
    }
    
    public boolean validarToken(String Token) throws Exception{
        boolean r=false;
        String sql="SELECT * FROM v_empleadosLogin WHERE LastToken='"+Token+"'";
        
        ConexionMySQL connMySQL=new ConexionMySQL();
        
        Connection conn=connMySQL.open();
        
        Statement stmt=(Statement)conn.createStatement();
        
        ResultSet rs=stmt.executeQuery(sql);
        
        if(rs.next())
            r=true;
        
        stmt.close();
        conn.close();
        connMySQL.close();
        
        return r;
    }
    
    public boolean eliminarToken(Usuario u) throws Exception{
        boolean r=false;
        String sql="UPDATE usuario SET LastToken='' WHERE UsuarioId=?";
        
        ConexionMySQL connMySQL=new ConexionMySQL();
        
        Connection conn=connMySQL.open();
        
        PreparedStatement prepst=conn.prepareCall(sql);
        
        prepst.setInt(1, u.getUsuarioId());
        prepst.execute();
        r=true;
        
        prepst.close();
        conn.close();
        connMySQL.close();
        
        return r;
    }
    
    public List<Pantalla> obtenerpantallas(int UsuarioId) throws Exception{
        String sql = "SELECT * FROM v_pantallas vp WHERE vp.UsuarioId = ?";
        
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        Connection conn = connMySQL.open();
        
        PreparedStatement prepst = conn.prepareStatement(sql);
        
        ResultSet rs = null;
        
        prepst.setInt(1, UsuarioId);
        
        rs = prepst.executeQuery();
        
        List<Pantalla> lstPantallas = new ArrayList<>();
        
        while (rs.next())
            lstPantallas.add(fillPantalla(rs));
        
        rs.close();
        prepst.close();
        connMySQL.close();
        
        return lstPantallas;
    }
    
    private Pantalla fillPantalla(ResultSet rs) throws Exception{
        Modulo m = new Modulo();
        Pantalla p = new Pantalla();
        
        m.setModuloId(rs.getInt("ModuloId"));
        m.setIdentificador(rs.getString("IdentificadorModulo"));
        m.setNombre(rs.getString("NombreModulo"));
        
        p.setPantallaId(rs.getInt("PantallaId"));
        p.setModulo(m);
        p.setIdentificador(rs.getString("IdentificadorPantalla"));
        p.setNombre(rs.getString("NombrePantalla"));
        p.setRuta(rs.getString("Ruta"));
        
        return p;
    }
}
