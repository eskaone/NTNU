package minpakke;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/kunder/") 
public class KundeService { 
    private static Map<String,Kunde> kunder = new HashMap<String,Kunde>() {{
        put("1", new Kunde("1", "Julenissen"));
    }}; 
    
    @GET 
    @Path("/{kundeId}") 
    @Produces(MediaType.APPLICATION_JSON) 
    public Kunde getKunde(@PathParam("kundeId") String kundeId) {
        if(!kunder.containsKey(kundeId)) {
            throw new NotFoundException();
        }
        return kunder.get(kundeId); 
    } 
    
    @GET 
    @Produces(MediaType.APPLICATION_JSON) 
    public Collection<Kunde> getKunder() { 
        return kunder.values(); 
    } 
    
    @POST 
    @Consumes(MediaType.APPLICATION_JSON) 
    public void addKunde(Kunde kunde) { 
        kunder.put(kunde.getId(), kunde); 
    }

    @DELETE
    @Path("{kundeId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteKunde(@PathParam("kundeId") String kundeId) {
        kunder.remove(kundeId);
    }

    @PUT
    @Path("{kundeId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void editKunde(@PathParam("kundeId") String kundeId, Kunde k) {
        if(!kunder.containsKey(kundeId)) {
            throw new NotFoundException();
        }
        kunder.replace(kundeId, k);
    }
}