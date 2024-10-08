
package org.utl.idgs.connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Alda
 */
public class ConexionMySQL {
    Connection conn;
    
    public Connection open(){
        String user = "";
        String password = "";
        String url = "jdbc:mysql://127.0.0.1:3306/[Base de Datos]?useSSL=false&"
                    +"allowPublicKeyRetrieval=true&"
                    +"useUnicode=true&characterEncoding=utf-8";
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    
    public void close(){
        if(conn != null){
            try{
                conn.close();
            } catch(Exception e){
                e.printStackTrace();
                System.out.println("Exception controlada");
            }
        }
    }
}
