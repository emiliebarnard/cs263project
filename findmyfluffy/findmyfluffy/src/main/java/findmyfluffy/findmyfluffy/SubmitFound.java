package findmyfluffy.findmyfluffy;

//Jersey
import javax.ws.rs.GET;
import javax.ws.rs.Path;

//Jersey
@Path("/")

public class SubmitFound{
	//Jersey
	@GET
	@Path("/found")
	public String printFoundInfo() {
		return "thanks for the info! we'll use it to help the kitty find their home. =^.^=";
	}

}
