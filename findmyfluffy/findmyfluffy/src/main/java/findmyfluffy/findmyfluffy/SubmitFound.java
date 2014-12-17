package findmyfluffy.findmyfluffy;

//Jersey
import javax.ws.rs.GET;
import javax.ws.rs.Path;

//Jersey
/**
 * SubmitFound Class
 * 
 * This acts as a confirmation page when one submits a found cat. It's currently inactive, but can be used if wanted.
 * 
 * @author emilie (Emilie Menard Barnard) - <a href="mailto:emilie@cs.ucsb.edu">emilie@cs.ucsb.edu</a>
 * @version 1.0
 */
@Path("/")

public class SubmitFound{
	//Jersey
	/**
	 * @return Returns a string to be printed into the webpage as a confirmation.
	 */
	@GET
	@Path("/found")
	public String printFoundInfo() {
		return "thanks for the info! we'll use it to help the kitty find their home. =^.^=";
	}
}
