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
  	  //System.out.println(foundCatInfo.chip);
	if (foundCatChip.equals("true")){
	  		  chipped = true;
	 }

  	  foundCat.setProperty("microchip", chipped);

//*****TO DO: ADD A CEHCK TO MAKE SURE ITS AN INT
  	  foundCat.setProperty("age", foundCatAge);
  	  foundCat.setProperty("sex", foundCatSex);
  	  foundCat.setProperty("breed", foundCatBreed);
  	  foundCat.setProperty("color", foundCatColor);
  	  foundCat.setProperty("area", foundCatArea);
  	  foundCat.setProperty("contactname", foundCatContactname);
  	  foundCat.setProperty("contactemail", foundCatContactemail);


  	//put found cat in datastore
	 // DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
  	DatastoreInfo.datastore.put(foundCat);

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