
package org.utl.idgs.core;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.utl.idgs.connection.ConexionMySQL;
import org.utl.idgs.model.Perfil;
import org.utl.idgs.model.Tipousuario;

/**
 *
 * @author Alda
 */
public class ControllerTipoUsuario {
    public List<Tipousuario> getAllTipoUsuario() throws Exception{
        String sql = "SELECT * FROM tipousuario";
        
        ConexionMySQL connMySQL=new ConexionMySQL();
        
        Connection conn = connMySQL.open();
        
        Statement stmt = (Statement)conn.createStatement();
        
        ResultSet rs = stmt.executeQuery(sql);
        
        List<Tipousuario> lstTipousuario = new ArrayList<>();
        while(rs.next())
            lstTipousuario.add(fill(rs));
        
        rs.close();
        stmt.close();
        conn.close();
        connMySQL.close();
        
        return lstTipousuario;
    }
    
    private Tipousuario fill(ResultSet rs) throws Exception{
        Tipousuario tu = new Tipousuario();
        
        tu.setTipoUsuarioId(rs.getInt("TipoUsuarioId"));
        tu.setNombre(rs.getString("Nombre"));
        
        return tu;
    }
}
