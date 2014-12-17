package findmyfluffy.findmyfluffy;

import findmyfluffy.findmyfluffy.DatastoreInfo;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;


/**
 * AddCat Class
 * 
 * This class is used to add a cat entity into the datastore.
 * 
 * @author emilie (Emilie Menard Barnard) - <a href="mailto:emilie@cs.ucsb.edu">emilie@cs.ucsb.edu</a>
 * @version 1.0
 * 
 */
public class AddCat{

	/**
	 * isChipped method.
	 * 
	 * This method sets the chip property of the cat so that all entries are uniform.
	 * 
	 * @param info The information passed in regarding chip data.
	 * @return A string, either "true" or "false."
	 */
	public String isChipped(String info){
		if (info == null){
			return "false";
		}
		else if (info.toLowerCase().contains("chip") && !info.toLowerCase().contains("nochip")){
			return "true";
		}
		else{
			return "false";
		}
	}
	/**
	 * findBreed method
	 * 
	 * This method sets the breed property of the cat so that all entries are uniform.
	 * 
	 * @param info The information passed in regarding breed data.
	 * @return A string containing the breed code.
	 */
	public String findBreed(String info){
		
		if (info == null) {
			return "";
		}
		else if (info.toLowerCase().contains("dsh")){
			return "dsh";
		}
		else if (info.toLowerCase().contains("dmh")){
			return "dmh";
		}
		else if (info.toLowerCase().contains("dlh")){
			return "dlh";
		}
		else if (info.toLowerCase().contains("bengal") || info.toLowerCase().contains("bengel")){
			return "bengal";
		}
		else if (info.toLowerCase().contains("burmese")){
			return "burmese";
		}
		else if (info.toLowerCase().contains("himi") || info.toLowerCase().contains("himalyan")){
			return "himalayan";
		}
		else if (info.toLowerCase().contains("lynx")){
			return "lynx";
		}
		else if (info.toLowerCase().contains("coon")){
			return "maine coon";
		}
		else if (info.toLowerCase().contains("persian")){
			return "persian";
		}
		else if (info.toLowerCase().contains("pixie bob")){
			return "pixie bob";
		}
		else if (info.toLowerCase().contains("russian blue")){
			return "russian blue";
		}
		else if (info.toLowerCase().contains("tonkinese")){
			return "tonkinese";
		}
		else if (info.toLowerCase().contains("siamese")){
			return "siamese";
		}
		else{
			return "";
		}
		//might have to add more breeds
		
	}
	
	/**
	 * findColor method
	 * 
	 * This method sets the color property of the cat so that all entries are uniform.
	 * 
	 * @param info The information passed in regarding color data.
	 * @return A string with the color code.
	 */
	public String findColor(String info){
		if (info == null) {
			return "";
		}
		else if (info.toLowerCase().contains("tabby")){
			if (info.toLowerCase().contains("orange")){
				return "orange tabby";
			}
			else if (info.toLowerCase().contains("brown")){
				return "brown tabby";
			}
			else if (info.toLowerCase().contains("grey") || info.toLowerCase().contains("gray")){
				return "grey tabby";
			}
			else if (info.toLowerCase().contains("black")){
				return "black tabby";
			}
			else{
				return "tabby";
			}	
		}
		else if (info.toLowerCase().contains("calico")){
			return "calico";
		}
		else if (info.toLowerCase().contains("torti") || info.toLowerCase().contains("tortoiseshell") || info.toLowerCase().contains("torbi")){
			return "tortoiseshell";
		}
		else if (info.toLowerCase().contains("seal")){
			return "seal point";
		}
		else if (info.toLowerCase().contains("lynx")){
			return "lynx";
		}
		else if (info.toLowerCase().contains("tuxedo") || (info.toLowerCase().contains("black") && info.toLowerCase().contains("white"))){
			return "tuxedo";
		}
		else if (info.toLowerCase().contains("black")){
			return "black";
		}
		else if (info.toLowerCase().contains("white")){
			return "white";
		}
		else if (info.toLowerCase().contains("grey") || info.toLowerCase().contains("gray")){
			return "grey";
		}
		else{
			return "";
		}
		
	}
	
	/**
	 * addLostCatEntry method
	 * 
	 * This method adds a lost cat entity into the datastore.
	 * 
	 * @param lostCatName The name data.
	 * @param lostCatChip The chip data.
	 * @param lostCatAge The age data.
	 * @param lostCatSex The sex data.
	 * @param lostCatBreed The breed data.
	 * @param lostCatColor The color data.
	 * @param lostCatArea The area data.
	 * @param lostCatContactname The contact name data.
	 * @param lostCatContactemail The contact email data.
	 */
	public void addLostCatEntry(String lostCatName, String lostCatChip, String lostCatAge, String lostCatSex, String lostCatBreed, String lostCatColor, String lostCatArea, String lostCatContactname, String lostCatContactemail){
		 
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

    	  lostCat.setProperty("age", lostCatAge);
    	  String sex = "male";
    	  if (lostCatSex.toLowerCase().contains("f".toLowerCase())){
    		  sex = "female";
    	  }
    	  lostCat.setProperty("sex", sex);
    	  lostCat.setProperty("breed", lostCatBreed);
    	  lostCat.setProperty("color", lostCatColor);
    	  lostCat.setProperty("area", lostCatArea);
    	  lostCat.setProperty("contactname", lostCatContactname);
    	  lostCat.setProperty("contactemail", lostCatContactemail);
    	  
    	//put lost cat in datastore
    	DatastoreInfo.datastore.put(lostCat);
	}
	
	/**
	 * addFoundCatEntry method
	 * 
	 * This method adds a found cat entity to the datastore.
	 * 
	 * @param foundCatName The name data.
	 * @param foundCatChip The chip data.
	 * @param foundCatAge The age data.
	 * @param foundCatSex The sex data.
	 * @param foundCatBreed The breed data.
	 * @param foundCatColor The color data.
	 * @param foundCatArea The area data.
	 * @param foundCatContactname The contact name data.
	 * @param foundCatContactemail The contact email data.
	 */
	public void addFoundCatEntry(String foundCatName, String foundCatChip, String foundCatAge, String foundCatSex, String foundCatBreed, String foundCatColor, String foundCatArea, String foundCatContactname, String foundCatContactemail){
		
		
		  if (foundCatName == null || foundCatName.isEmpty()){
			  foundCatName = "unkown";
		  }
		
			Key foundCatKey = KeyFactory.createKey("foundCat", foundCatName);
		  Entity foundCat = new Entity("foundcat", foundCatKey);

		  foundCat.setProperty("catname", foundCatName.toLowerCase());

		  boolean chipped = false;
	  if (foundCatChip.equals("true")){
				  chipped = true;
	   }

		  foundCat.setProperty("microchip", chipped);

		  foundCat.setProperty("age", foundCatAge);
		  
    	  String sex = "male";
    	  if (foundCatSex.toLowerCase().contains("f".toLowerCase())){
    		  sex = "female";
    	  }
		  foundCat.setProperty("sex", sex);
		  foundCat.setProperty("breed", foundCatBreed);
		  foundCat.setProperty("color", foundCatColor);
		  foundCat.setProperty("area", foundCatArea);
		  foundCat.setProperty("contactname", foundCatContactname);
		  foundCat.setProperty("contactemail", foundCatContactemail);


		//put found cat in datastore
		DatastoreInfo.datastore.put(foundCat);
	}
}
