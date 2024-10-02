
package org.utl.idgs.rest;

import com.google.gson.Gson;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import org.utl.idgs.core.ControllerTipoUsuario;
import org.utl.idgs.model.Tipousuario;

/**
 *
 * @author Alda
 */
@Path("tipousuarios")
public class RESTTipoUsuario {
    @Path("getAll")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response getAll() throws Exception{
        String out=null;
        Gson gson=new Gson();
        List<Tipousuario> lstTipoUsuario = new ArrayList<>();
        ControllerTipoUsuario ctu = new ControllerTipoUsuario();
        
        try{
            lstTipoUsuario = ctu.getAllTipoUsuario();
            out = gson.toJson(lstTipoUsuario);
        } catch(Exception ex){
            out="""
                {"exception":"?"}
                """;
            out=String.format(out, ex.toString());
        }
        
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
