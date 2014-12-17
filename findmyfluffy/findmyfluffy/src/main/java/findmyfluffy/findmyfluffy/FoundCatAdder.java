package findmyfluffy.findmyfluffy;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import java.io.IOException;

import findmyfluffy.findmyfluffy.DatastoreInfo;

/**
 * FoundCatAdder class
 * 
 * This is a servlet class that adds a found cat entity to the datastore. It's called by UploadFound.
 * 
 * @author emilie (Emilie Menard Barnard) - <a href="mailto:emilie@cs.ucsb.edu">emilie@cs.ucsb.edu</a>
 * @version 1.0
 *
 */
public class FoundCatAdder extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String foundCatName = request.getParameter("name");
		String foundCatChip = request.getParameter("chip");
		String foundCatAge = request.getParameter("age");
		String foundCatSex = request.getParameter("sex");
		String foundCatBreed = request.getParameter("breed");
		String foundCatColor = request.getParameter("color");
		String foundCatArea = request.getParameter("area");
		String foundCatContactname = request.getParameter("contactname");
		String foundCatContactemail = request.getParameter("contactemail");

		if (foundCatName == null || foundCatName.isEmpty()){
			  foundCatName = "unkown";
		  }  

		Key foundCatKey = KeyFactory.createKey("foundCat", foundCatName);
  	  Entity foundCat = new Entity("foundcat", foundCatKey);

  	  foundCat.setProperty("catname", foundCatName);

  	  boolean chipped = false;
	if (foundCatChip.equals("true")){
	  		  chipped = true;
	 }

  	  foundCat.setProperty("microchip", chipped);
  	  foundCat.setProperty("age", foundCatAge);
  	  foundCat.setProperty("sex", foundCatSex);
  	  foundCat.setProperty("breed", foundCatBreed);
  	  foundCat.setProperty("color", foundCatColor);
  	  foundCat.setProperty("area", foundCatArea);
  	  foundCat.setProperty("contactname", foundCatContactname);
  	  foundCat.setProperty("contactemail", foundCatContactemail);


  	//put found cat in datastore
  	DatastoreInfo.datastore.put(foundCat);
	}
}