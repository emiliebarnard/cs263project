package findmyfluffy.findmyfluffy;

//Jersey
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

//Jersey
@Path("/")
/**
 * SubmitLost Class
 * 
 * This acts as a confirmation page when one submits a lost cat. It's currently inactive, but can be used if wanted.
 * 
 * @author emilie (Emilie Menard Barnard) - <a href="mailto:emilie@cs.ucsb.edu">emilie@cs.ucsb.edu</a>
 * @version 1.0
 */
public class SubmitLost {
	//Jersey
	@GET
	@Path("/lost")
	/**
	 * @return Returns a string to be printed into the webpage as a confirmation.
	 */
	public String printLostInfo(@DefaultValue("your cat") @QueryParam("catname") String catname) {
		return "thanks for the info! we hope you find " + catname + " soon. =^.^="
				+ "<br><a href=\"/\">back to admin page</a>";
	}

}