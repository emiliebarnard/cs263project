package findmyfluffy.findmyfluffy;

import findmyfluffy.findmyfluffy.DatastoreInfo;


import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class AddCat{
	
	public void addLostCatEntry(String lostCatName, String lostCatChip, String lostCatAge, String lostCatSex, String lostCatBreed, String lostCatColor, String lostCatArea, String lostCatContactname, String lostCatContactemail){
    	  
    	  Key lostCatKey = KeyFactory.createKey("lostCat", lostCatName);
    	  Entity lostCat = new Entity("lostcat", lostCatKey);
    	  
    	  lostCat.setProperty("catname", lostCatName);
    	  
    	  boolean chipped = false;
    	  //System.out.println(lostCatInfo.chip);
      if (lostCatChip.equals("chip")){
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
    	DatastoreInfo.datastore.put(lostCat);
	}
	
	public void addFoundCatEntry(String foundCatName, String foundCatChip, String foundCatAge, String foundCatSex, String foundCatBreed, String foundCatColor, String foundCatArea, String foundCatContactname, String foundCatContactemail){

		  Key foundCatKey = KeyFactory.createKey("foundCat", foundCatName);
		  Entity foundCat = new Entity("foundcat", foundCatKey);

		  foundCat.setProperty("catname", foundCatName);

		  boolean chipped = false;
		  //System.out.println(foundCatInfo.chip);
	  if (foundCatChip.equals("chip")){
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
	}
}
