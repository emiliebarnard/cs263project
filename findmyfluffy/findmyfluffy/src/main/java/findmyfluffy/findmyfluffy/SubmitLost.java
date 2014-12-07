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

public class SubmitLost {
	//Jersey
	@GET
//	@Produces(MediaType.TEXT_HTML)
	@Path("/lost")
	
	public String printLostInfo(@DefaultValue("your cat") @QueryParam("catname") String catname) {
		return "thanks for the info! we hope you find " + catname + " soon. =^.^=";
	}

}