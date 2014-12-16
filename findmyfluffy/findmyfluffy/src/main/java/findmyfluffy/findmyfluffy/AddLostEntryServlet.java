package findmyfluffy.findmyfluffy;

import findmyfluffy.findmyfluffy.Cat;
import findmyfluffy.findmyfluffy.DatastoreInfo;

import com.google.appengine.api.datastore.DatastoreFailureException;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gson.Gson;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;

import static com.google.appengine.api.taskqueue.TaskOptions.Builder.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddLostEntryServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		//nothing to get
	}	
	
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
	  
	  //System.out.println("here");
	  //resp.setContentType("application/json");
		
		//System.out.println("stuff: " + req);

	  	  Gson gson = new Gson();
	  	  
	  	try {
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = req.getReader().readLine()) != null) {
                sb.append(s);
            }
            
            //System.out.println(sb.toString());
 
            Cat lostCatInfo = (Cat) gson.fromJson(sb.toString(), Cat.class);
            
            //System.out.println(lostCat.age);
            
          //TODO: have to add a check to see if these were left blank
//          Queue queue = QueueFactory.getDefaultQueue();
//          
//          try {
      	  
        	    String lostCatName = lostCatInfo.petname;
      	  
      	  //Key lostCatKey = KeyFactory.createKey("lostCat", lostCatName);
//      	  Entity lostCat = new Entity("lostcat", lostCatKey);
//      	  
//      	  lostCat.setProperty("catname", lostCatName);
      	  
        	    String chipped = "false";
            	  //System.out.println(lostCatInfo.chip);
              	if (lostCatInfo.chip != null && !lostCatInfo.chip.isEmpty()) {
              		if (lostCatInfo.chip.equals("chip")){
                  		  chipped = "true";
              		}
              	}
      	
//      	  lostCat.setProperty("microchip", chipped);
//      	  
//  //*****TO DO: ADD A CEHCK TO MAKE SURE ITS AN INT
//      	  lostCat.setProperty("age", lostCatInfo.age);
//      	  lostCat.setProperty("sex", lostCatInfo.sex);
//      	  lostCat.setProperty("breed", lostCatInfo.breed);
//      	  lostCat.setProperty("color", lostCatInfo.color);
//      	  lostCat.setProperty("area", lostCatInfo.area);
//      	  lostCat.setProperty("contactname", lostCatInfo.contactname);
//      	  lostCat.setProperty("contactemail", lostCatInfo.contactemail);
      	  
      	  
      	//put lost cat in datastore
    	 // DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
      	//DatastoreInfo.datastore.put(lostCat);
      	
      	//this calls the function to add the cat using the task queue	
        Queue queue = QueueFactory.getDefaultQueue();
//       System.out.println("before queue");
//     System.out.println(lostCatName);
//     System.out.println(chipped);
//     System.out.println(lostCatInfo.age);
//     System.out.println(lostCatInfo.sex);
//     System.out.println(lostCatInfo.breed);
//     System.out.println(lostCatInfo.color);
//     System.out.println(lostCatInfo.area);
//     System.out.println(lostCatInfo.contactname);
//     System.out.println(lostCatInfo.contactemail);
        
        //queue.add(withUrl("/lostcatadder").param("name", lostCatName).param("chip", chipped).param("age", lostCatInfo.age).param("sex", lostCatInfo.sex).param("breed", lostCatInfo.breed).param("color", lostCatInfo.color).param("area", lostCatInfo.area).param("contactname", lostCatInfo.contactname).param("contactemail", lostCatInfo.contactemail));
       //System.out.println("after queue");
        //once that task is sent off, then we also call a function to find matches using lostCatInfo:
        
        
      	  //AddCat add = new AddCat();
    	  //add.addLostCatEntry(lostCatName, chipped, lostCatInfo.age, lostCatInfo.sex, lostCatInfo.breed, lostCatInfo.color, lostCatInfo.area, lostCatInfo.contactname, lostCatInfo.contactemail);
    	  
    	  
    	  //redirect to thank you page
    	 // resp.sendRedirect("/submit/lost/?catname=" + lostCatName);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
	  
//	  	} catch (DatastoreFailureException e) {
//	  	}

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