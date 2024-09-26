
package org.utl.idgs.rest;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import org.utl.idgs.core.ControllerLogin;
import org.utl.idgs.model.Pantalla;
import org.utl.idgs.model.Usuario;

/**
 *
 * @author Alda
 */
@Path("log")
public class RESTLogin extends Application{
    @Path("in")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response login(@FormParam("datos") @DefaultValue("") String datos) throws Exception{
        String out = null;
        Gson gson = new Gson();
        Usuario u = new Usuario();
        ControllerLogin cl = new ControllerLogin();
        
        try{
            u = gson.fromJson(datos, Usuario.class);
            u = cl.login(u.getCuenta(), u.getContrasena());
            if (u != null){
                u.setLastToken();
                cl.generarToken(u.getUsuarioId(), u.getLastToken());
                out = gson.toJson(u);
            } else{
                out = """
                      {"error":"Las credenciales son incorrectas, intente de nuevo."}
                      """;
            }
        } catch (Exception ex){
            ex.printStackTrace();
            out = """
                  {"exception": "?"}
                  """;
            out = String.format(out, ex.toString());
        }
        
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("pantallas")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response obtenerPantallas(@FormParam("usuario") @DefaultValue("") String usuario) throws Exception{
        String out = null;
        Gson gson = new Gson();
        Usuario u = new Usuario();
        List<Pantalla> lst = new ArrayList<>();
        ControllerLogin cl = new ControllerLogin();
        
        try{
            u = gson.fromJson(usuario, Usuario.class);
            if (u != null && cl.validarToken(u.getLastToken())){
                lst = cl.obtenerpantallas(u.getUsuarioId());
                out = gson.toJson(lst);
            } else{
                out="""
                    {"error": "Es posible que no estes registrado o no tengas permisos"}
                    """;
            }
        } catch (Exception ex){
            ex.printStackTrace();
            out="""
                {"exception":"?"}
                """;
            out=String.format(out, ex.toString());
        }
        
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("out")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response logout(@FormParam("usuario") @DefaultValue("") String usuario) throws Exception{
        String out=null;
        Gson gson=new Gson();
        Usuario u = new Usuario();
        ControllerLogin cl=new ControllerLogin();
        
        try{
            u = gson.fromJson(usuario, Usuario.class);
            if (cl.eliminarToken(u)){
                out = """
                      {"ok": "Eliminacion de Token Correcta."}
                      """;
            } else{
                out = """
                      {"error": "Eliminacion de token no realizada."}
                      """;
            }
        } catch(JsonParseException jpe){
            out = """
                  {"error":"Eliminacion no concedido"}
                  """;
            jpe.printStackTrace();
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
