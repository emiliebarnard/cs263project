package findmyfluffy.findmyfluffy;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddLostEntryServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
//		doPost(request, response);
	}	
	
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
	  
	  //TODO: have to add a check to see if these were left blank
	  
	  
	  String lostCatName = req.getParameter("petname");
	  
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
	  datastore.put(lostCat);
	  
	  //redirect to thank you page
	  resp.sendRedirect("/submit/lost/?catname=" + lostCatName);
	  
	  //do more stuff here with datastore stuff
	  
	  //want to get the info submitted from the user and add it to our storage
	  
	  //should have different types for lost/found
	  //or maybe just some sort of field that has if it's lost or found
	  
//    UserService userService = UserServiceFactory.getUserService();
//    User user = userService.getCurrentUser();
//
//    String guestbookName = req.getParameter("guestbookName");
//    Key guestbookKey = KeyFactory.createKey("Guestbook", guestbookName);
//    String content = req.getParameter("content");
//    Date date = new Date();
//    Entity greeting = new Entity("Greeting", guestbookKey);
//    greeting.setProperty("user", user);
//    greeting.setProperty("date", date);
//    greeting.setProperty("content", content);
//
//    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
//    datastore.put(greeting);
//
//    resp.sendRedirect("/guestbook.jsp?guestbookName=" + guestbookName);
  }
}