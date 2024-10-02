
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
import org.utl.idgs.model.Perfil;

/**
 *
 * @author Alda
 */
public class ControllerRol {
    public List<Perfil> getAllPerfil() throws Exception{
        String sql = "SELECT * FROM perfil";
        
        ConexionMySQL connMySQL=new ConexionMySQL();
        
        Connection conn = connMySQL.open();
        
        Statement stmt = (Statement)conn.createStatement();
        
        ResultSet rs = stmt.executeQuery(sql);
        
        List<Perfil> lstPerfil = new ArrayList<>();
        while(rs.next())
            lstPerfil.add(fill(rs));
        
        rs.close();
        stmt.close();
        conn.close();
        connMySQL.close();
        
        return lstPerfil;
    }
    
    private Perfil fill(ResultSet rs) throws Exception{
        Perfil p = new Perfil();
        
        p.setPerfilId(rs.getInt("PerfilId"));
        p.setNombre(rs.getString("Nombre"));
        p.setActivo(rs.getInt("Activo"));
        
        return p;
    }
    
    public int guardarPerfil(Perfil p) throws Exception{
        String sql = "{CALL guardarPerfil(?, ?, ?)}";
        
        int PerfilIdGenerado = -1;
        
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        Connection conn = connMySQL.open();
        
        CallableStatement clblsmt = conn.prepareCall(sql);
        
        clblsmt.setString(1, p.getNombre());
        clblsmt.setInt(2, p.getActivo());
        
        clblsmt.registerOutParameter(3, Types.INTEGER);
        
        clblsmt.executeUpdate();
        
        PerfilIdGenerado = clblsmt.getInt(3);
        
        p.setPerfilId(PerfilIdGenerado);
        
        clblsmt.close();
        conn.close();
        connMySQL.close();
        
        return PerfilIdGenerado;
    }
    
    public void actualizarPerfil(Perfil p) throws Exception{
        String sql = "{CALL actualizarPerfil(?, ?, ?)}";
        
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        Connection conn = connMySQL.open();
        
        CallableStatement clblsmt = conn.prepareCall(sql);
        
        clblsmt.setInt(1, p.getPerfilId());
        clblsmt.setString(2, p.getNombre());
        clblsmt.setInt(3, p.getActivo());
        
        clblsmt.executeUpdate();
        
        clblsmt.close();
        conn.close();
        connMySQL.close();
    }
    
    public boolean eliminarPerfil(int PerfilId) throws Exception{
        boolean respuesta = false;
        String sql = "DELETE FROM perfil WHERE PerfilId = ?";
        
        ConexionMySQL connMySQL=new ConexionMySQL();
        
        Connection conn=connMySQL.open();
        
        PreparedStatement prepst=conn.prepareCall(sql);
        
        prepst.setInt(1, PerfilId);
        prepst.execute();
        respuesta = true;
        
        prepst.close();
        conn.close();
        connMySQL.close();
        
        return respuesta;
    }
}
