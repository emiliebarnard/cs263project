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

// The Worker servlet should be mapped to the "/worker" URL.
public class LostCatAdder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	String lostCatName = request.getParameter("name");
    	String lostCatChip = request.getParameter("chip");
    	String lostCatAge = request.getParameter("age");
    	String lostCatSex = request.getParameter("sex");
    	String lostCatBreed = request.getParameter("breed");
    	String lostCatColor = request.getParameter("color");
    	String lostCatArea = request.getParameter("area");
    	String lostCatContactname = request.getParameter("contactname");
    	String lostCatContactemail = request.getParameter("contactemail");
    	
    	if (lostCatName == null || lostCatName.isEmpty()){
			  lostCatName = "unkown";
		  }  
  	 
    	Key lostCatKey = KeyFactory.createKey("lostCat", lostCatName);
  	  	Entity lostCat = new Entity("lostcat", lostCatKey);
  	  
  	  lostCat.setProperty("catname", lostCatName.toLowerCase());
  	  
  	  boolean chipped = false;
    if (lostCatChip.equals("true")){
      		  chipped = true;
     }
  	
  	  lostCat.setProperty("microchip", chipped);
  	  lostCat.setProperty("age", lostCatAge);
  	  lostCat.setProperty("sex", lostCatSex);
  	  lostCat.setProperty("breed", lostCatBreed);
  	  lostCat.setProperty("color", lostCatColor);
  	  lostCat.setProperty("area", lostCatArea);
  	  lostCat.setProperty("contactname", lostCatContactname);
  	  lostCat.setProperty("contactemail", lostCatContactemail);
  	  
  	  
  	//put lost cat in datastore
  	DatastoreInfo.datastore.put(lostCat);
    }
}