package findmyfluffy.findmyfluffy;

import java.util.Date;
import java.util.logging.Level;

import com.google.appengine.api.memcache.*;

import java.lang.Object;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

import java.io.IOException;
import java.util.Properties;

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
  	  //System.out.println(lostCatInfo.chip);
    if (lostCatChip.equals("true")){
      		  chipped = true;
     }
  	
  	  lostCat.setProperty("microchip", chipped);
  	  
//*****TO DO: ADD A CEHCK TO MAKE SURE ITS AN INT
  	  lostCat.setProperty("age", lostCatAge);
  	  lostCat.setProperty("sex", lostCatSex);
  	  lostCat.setProperty("breed", lostCatBreed);
  	  lostCat.setProperty("color", lostCatColor);
  	  lostCat.setProperty("area", lostCatArea);
  	  lostCat.setProperty("contactname", lostCatContactname);
  	  lostCat.setProperty("contactemail", lostCatContactemail);
  	  
  	  
  	//put lost cat in datastore
	 // DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
//  	System.out.println("here!");
  	DatastoreInfo.datastore.put(lostCat);

//        String key = request.getParameter("key");
//        String value = request.getParameter("value");
//        
//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        Date date = new Date();
//        
//        Entity ourInfo = new Entity("TaskData");
//        ourInfo.setProperty("key", key);
//        ourInfo.setProperty("value", value);
////        ourInfo.setProperty("date", dateFormat.format(date));
//        ourInfo.setProperty("date", date);
//        
//        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
//        datastore.put(ourInfo);
//        
//        
//        MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
//        syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
//        
//        syncCache.put(key, value); // populate cache
        
    }
}