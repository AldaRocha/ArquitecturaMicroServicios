
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
import org.utl.idgs.core.ControllerRol;
import org.utl.idgs.model.Perfil;

/**
 *
 * @author Alda
 */
@Path("roles")
public class RESTRol {
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getAll() throws Exception{
        String out=null;
        Gson gson=new Gson();
        List<Perfil> lstPerfil = new ArrayList<>();
        ControllerRol cr = new ControllerRol();
        
        try{
            lstPerfil = cr.getAllPerfil();
            out = gson.toJson(lstPerfil);
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
    public Response guardar(@FormParam("datosPerfil") @DefaultValue("") String datosPerfil) throws Exception{
        String out = null;
        Gson gson = new Gson();
        Perfil p = new Perfil();
        ControllerRol cr = new ControllerRol();
        
        try{
            p = gson.fromJson(datosPerfil, Perfil.class);
            cr.guardarPerfil(p);
            out = gson.toJson(p);
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
    public Response actualizar(@FormParam("datosPerfil") @DefaultValue("") String datosPerfil) throws Exception{
        String out = null;
        Gson gson = new Gson();
        Perfil p = new Perfil();
        ControllerRol cr = new ControllerRol();
        
        try{
            p = gson.fromJson(datosPerfil, Perfil.class);
            cr.actualizarPerfil(p);
            out = gson.toJson(p);
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
    public Response eliminar(@FormParam("datosPerfil") @DefaultValue("") String datosPerfil) throws Exception{
        String out = null;
        Gson gson = new Gson();
        Perfil p = new Perfil();
        ControllerRol cr = new ControllerRol();
        
        try{
            p = gson.fromJson(datosPerfil, Perfil.class);
            cr.eliminarPerfil(p.getPerfilId());
            out = gson.toJson(p);
        } catch(Exception ex){
            out="""
                {"exception":"?"}
                """;
            out=String.format(out, ex.toString());
        }
        
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
