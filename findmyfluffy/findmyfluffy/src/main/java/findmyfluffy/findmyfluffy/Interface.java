package findmyfluffy.findmyfluffy;


//Jersey
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/")

public class Interface {
	@GET
	@Path("/home")
	public String testMethod() {
		return "find my fluffy service coming soon! =^.^=";
	}	
}
