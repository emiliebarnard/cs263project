package findmyfluffy.findmyfluffy;

//Jersey
import javax.ws.rs.GET;
import javax.ws.rs.Path;

//Jersey
@Path("/")

public class Submit {
	//Jersey
	@GET
	@Path("/done")
	public String testMethod() {
		return "thanks for the info! =^.^=";
	}

}
