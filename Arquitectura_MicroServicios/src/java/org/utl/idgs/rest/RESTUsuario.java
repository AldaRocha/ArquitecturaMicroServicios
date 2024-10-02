
package org.utl.idgs.rest;

import com.google.gson.Gson;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import org.utl.idgs.core.ControllerUsuario;
import org.utl.idgs.model.Usuario;

/**
 *
 * @author Alda
 */
@Path("usuarios")
public class RESTUsuario {
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getAll() throws Exception{
        String out = null;
        Gson gson=new Gson();
        List<Usuario> lstUsuario = new ArrayList<>();
        ControllerUsuario cu = new ControllerUsuario();
        
        try{
            lstUsuario = cu.getAllUsuario();
            out = gson.toJson(lstUsuario);
        } catch(Exception ex){
            out="""
                {"exception":"?"}
                """;
            out=String.format(out, ex.toString());
        }
        
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("guardar")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response guardar(@FormParam("datosUsuario") @DefaultValue("") String datosUsuario) throws Exception{
        String out = null;
        Gson gson = new Gson();
        Usuario u = new Usuario();
        ControllerUsuario cu = new ControllerUsuario();
        
        try{
            u = gson.fromJson(datosUsuario, Usuario.class);
            cu.guardarUsuario(u);
            out = gson.toJson(u);
        } catch(Exception ex){
            out="""
                {"exception":"?"}
                """;
            out=String.format(out, ex.toString());
        }
        
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("actualizar")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response actualizar(@FormParam("datosUsuario") @DefaultValue("") String datosUsuario) throws Exception{
        String out = null;
        Gson gson = new Gson();
        Usuario u = new Usuario();
        ControllerUsuario cu = new ControllerUsuario();
        
        try{
            u = gson.fromJson(datosUsuario, Usuario.class);
            cu.actualizarUsuario(u);
            out = gson.toJson(u);
        } catch(Exception ex){
            out="""
                {"exception":"?"}
                """;
            out=String.format(out, ex.toString());
        }
        
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("eliminar")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response eliminar(@FormParam("datosUsuario") @DefaultValue("") String datosUsuario) throws Exception{
        String out = null;
        Gson gson = new Gson();
        Usuario u = new Usuario();
        ControllerUsuario cu = new ControllerUsuario();
        
        try{
            u = gson.fromJson(datosUsuario, Usuario.class);
            cu.eliminarUsuario(u.getUsuarioId());
            out = gson.toJson(u);
        } catch(Exception ex){
            out="""
                {"exception":"?"}
                """;
            out=String.format(out, ex.toString());
        }
        
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("cambiarContrasenia")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response cambiarContrasenia(@FormParam("datosUsuario") @DefaultValue("") String datosUsuario) throws Exception{
        String out = null;
        Gson gson = new Gson();
        Usuario u = new Usuario();
        ControllerUsuario cu = new ControllerUsuario();
        
        try{
            u = gson.fromJson(datosUsuario, Usuario.class);
            cu.cambiarContraseña(u);
            out = gson.toJson(u);
        } catch(Exception ex){
            ex.printStackTrace();
            out="""
                {"exception":"?"}
                """;
            out=String.format(out, ex.toString());
        }
        
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
