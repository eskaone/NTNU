package minpakke;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/Javatext")
public class Javatext {
    private static String text = "default";

    @POST
    @Path("/{NewText}")
    public void setText(@PathParam("NewText") String txt){
        text = txt;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getText(){
        return text;
    }
}