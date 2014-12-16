package findmyfluffy.findmyfluffy;

import static com.google.appengine.api.taskqueue.TaskOptions.Builder.withUrl;
import findmyfluffy.findmyfluffy.AddCat;
import findmyfluffy.findmyfluffy.Cat;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddFoundEntryServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
//		doPost(request, response);
	}	
	
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
	  //resp.setContentType("application/json");
		
//		System.out.println("stuff: " + req.getParameter("jsonInfo"));

	  	  Gson gson = new Gson();
	  	  
	  	try {
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = req.getReader().readLine()) != null) {
                sb.append(s);
            }
            
            //System.out.println(sb.toString());
 
            Cat foundCatInfo = (Cat) gson.fromJson(sb.toString(), Cat.class);
            
            //System.out.println(lostCat.age);
            
          //TODO: have to add a check to see if these were left blank
      	  
      	  
      	  String foundCatName = foundCatInfo.petname;
      	  
      	  //Key foundCatKey = KeyFactory.createKey("foundCat", foundCatName);
      	  //Entity foundCat = new Entity("foundcat", foundCatKey);
      	  
      	  //foundCat.setProperty("catname", foundCatName);
      	  
      	  String chipped = "false";
      	  //System.out.println(lostCatInfo.chip);
      	  
      	if (foundCatInfo.chip != null && !foundCatInfo.chip.isEmpty()) {
      		if (foundCatInfo.chip.equals("chip")){
          		  chipped = "true";
      		}
      	}
      	
      	if (foundCatInfo.sex == "m"){
        	foundCatInfo.sex = "male";
        }
        
        if (foundCatInfo.sex == "f"){
        	foundCatInfo.sex = "female";
        }
        
      	
      	  //foundCat.setProperty("microchip", chipped);
      	  
  //*****TO DO: ADD A CEHCK TO MAKE SURE ITS AN INT
//      	  foundCat.setProperty("age", foundCatInfo.age);
//      	  foundCat.setProperty("sex", foundCatInfo.sex);
//      	  foundCat.setProperty("breed", foundCatInfo.breed);
//      	  foundCat.setProperty("color", foundCatInfo.color);
//      	  foundCat.setProperty("area", foundCatInfo.area);
//      	  foundCat.setProperty("contactname", foundCatInfo.contactname);
//      	  foundCat.setProperty("contactemail", foundCatInfo.contactemail);
//      	  
      	  
      	//put lost cat in datastore
    	  //DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
      	  //System.out.println(foundCatName);
      	  //AddCat add = new AddCat();
      	  //add.addFoundCatEntry(foundCatName, chipped, foundCatInfo.age, foundCatInfo.sex, foundCatInfo.breed, foundCatInfo.color, foundCatInfo.area, foundCatInfo.contactname, foundCatInfo.contactemail);
//        System.out.println("before queue");
      	Queue queue = QueueFactory.getDefaultQueue();
//        System.out.println(foundCatName);
//        System.out.println(chipped);
//        System.out.println(foundCatInfo.age);
//        System.out.println(foundCatInfo.sex);
//        System.out.println(foundCatInfo.breed);
//        System.out.println(foundCatInfo.color);
//        System.out.println(foundCatInfo.area);
//        System.out.println(foundCatInfo.contactname);
//        System.out.println(foundCatInfo.contactemail);
        queue.add(withUrl("/foundcatadder").param("name", foundCatName).param("chip", chipped).param("age", foundCatInfo.age).param("sex", foundCatInfo.sex).param("breed", foundCatInfo.breed).param("color", foundCatInfo.color).param("area", foundCatInfo.area).param("contactname", foundCatInfo.contactname).param("contactemail", foundCatInfo.contactemail));
//        System.out.println("after queue");
    	  //DatastoreInfo.datastore.put(foundCat);
    	  
    	  //redirect to thank you page
    	 // resp.sendRedirect("/submit/lost/?catname=" + lostCatName);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
	  


	  //TODO: have to add a check to see if these were left blank
	  
	  
	  /*String lostCatName = req.getParameter("petname");
	  
	  Key lostCatKey = KeyFactory.createKey("lostcat", lostCatName);
	  Entity lostCat = new Entity("lostcat", lostCatKey);
	  
	  lostCat.setProperty("catname", lostCatName);
	  
	  boolean chipped = false;
	  System.out.println(req.getParameter("chip"));
	  
	  try{
		  if (req.getParameter("chip").equals("chip")){
			  System.out.println("true");
			  chipped = true;
		  }
	  } catch(NullPointerException e){
		//do nothing  
	  }
	  
	  lostCat.setProperty("microchip", chipped);
	  
	  //TODO: have to check and see if we can parse this to an int first
	  int catAge = Integer.parseInt(req.getParameter("age"));
	  lostCat.setProperty("age", catAge);
	 
	  String catSex = req.getParameter("sex");
	  lostCat.setProperty("sex", catSex);
	  
	  String breed = req.getParameter("breed");
	  lostCat.setProperty("breed", breed);
	  
	  String color = req.getParameter("color");
	  lostCat.setProperty("color", color);
	  
	  String area = req.getParameter("area");
	  lostCat.setProperty("area", area);
	  
	  String contactName = req.getParameter("contactname");
	  lostCat.setProperty("contactname", contactName);
	  
	  String contactEmail = req.getParameter("contactemail");
	  lostCat.setProperty("contactemail", contactEmail);
	  
//	  //flag that this is a lost cat
//	  lostCat.setProperty("lostOrFound", "lost");
	    
	  //put lost cat in datastore
	  DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	  datastore.put(lostCat);*/
	  
	  	
	  	
//JSON testing:	  	
//      resp.setStatus(200);
//      resp.setContentType("application/json");
//      PrintWriter out = resp.getWriter();
//      out.println("{}");
//      out.close();

	  
	  //do more stuff here with datastore stuff
	  
	  //want to get the info submitted from the user and add it to our storage
	  
	  //should have different types for lost/found
	  //or maybe just some sort of field that has if it's lost or found
  }
}