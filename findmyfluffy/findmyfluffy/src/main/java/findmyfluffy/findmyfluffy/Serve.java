package findmyfluffy.findmyfluffy;

import findmyfluffy.findmyfluffy.MemCacheInfo;

//Jersey
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

//Jersey
/**
 * Serve Class
 * 
 * This class is essentially a confirmation page when you upload a csv file. It also uses memcache to display the first cat's name so you can confirm file contents. 
 * 
 * @author emilie (Emilie Menard Barnard) - <a href="mailto:emilie@cs.ucsb.edu">emilie@cs.ucsb.edu</a>
 * @version 1.0
 */
@Path("/")

public class Serve {
	//Jersey
	/**
	 * @param key The blobkey info passed via Jersey.
	 * @return Returns a string that's printed to the webpage, confirming the upload with the blob key and first cat's name.
	 */
	@GET
	@Path("/data")
	
	public String printLostInfo(@DefaultValue("default-key") @QueryParam("blob-key") String key) {
		String name = MemCacheInfo.syncCache.get(key).toString();
		
		return "thanks for the info regarding " + name + " and the other kitties! we've saved your file as " + key 
				+ "<br><a href=\"/admin.jsp\">back to admin page</a>";
	}

}