package findmyfluffy.findmyfluffy;

//Jersey
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;

//Jersey
@Path("/")

public class Serve {
	//Jersey
	@GET
//	@Produces(MediaType.TEXT_HTML)
	@Path("/data")
	
	public String printLostInfo(@DefaultValue("default-key") @QueryParam("blob-key") String key) {
		return "thanks for the info! we've saved your file as " + key ;
	}

}