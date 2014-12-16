package findmyfluffy.findmyfluffy;

//Jersey
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

//Jersey
@Path("/")

public class SubmitLost {
	//Jersey
	@GET
	@Path("/lost")
	
	public String printLostInfo(@DefaultValue("your cat") @QueryParam("catname") String catname) {
		return "thanks for the info! we hope you find " + catname + " soon. =^.^="
				+ "<br><a href=\"/\">back to admin page</a>";
	}

}