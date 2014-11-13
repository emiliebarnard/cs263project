package findmyfluffy.findmyfluffy;

//Jersey
import javax.ws.rs.GET;
import javax.ws.rs.Path;

//Jersey
@Path("/")

public class SubmitLost {
	//Jersey
	@GET
	@Path("/lost")
	public String printLostInfo() {
		return "thanks for the info! we hope you find your lost cat soon. =^.^=";
	}

}
