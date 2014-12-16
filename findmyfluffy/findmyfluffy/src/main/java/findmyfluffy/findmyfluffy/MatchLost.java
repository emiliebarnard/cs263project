package findmyfluffy.findmyfluffy;

import findmyfluffy.findmyfluffy.Cat;
import findmyfluffy.findmyfluffy.DatastoreInfo;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Entity;
import com.google.gson.Gson;

public class MatchLost extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		//resp.setContentType("application/json");
		
//		System.out.println("in MatchLost");
//		System.out.println(req.getParameter("n"));
//		System.out.println(req.getParameter("chip"));
//		System.out.println(req.getParameter("age"));
//		System.out.println(req.getParameter("s"));
//		System.out.println(req.getParameter("b"));
//		System.out.println(req.getParameter("c"));
//		System.out.println(req.getParameter("a"));
//		System.out.println(req.getParameter("cn"));
//		System.out.println(req.getParameter("ce"));
		
		String petname = req.getParameter("n").toLowerCase();
		String chip = req.getParameter("chip");
		String age = req.getParameter("age");
		String sex = req.getParameter("s");
		String breed = req.getParameter("b");
		String color = req.getParameter("c");
		String area = req.getParameter("a");
		String contactname = req.getParameter("cn");
		String contactemail = req.getParameter("ce");
		
		//now we need to search the datastore for matches
		//if three of name, age, sex, breed, color, chip, area match, return result
		//Future work: should this be changed to something else?
		Filter matches = new FilterPredicate("catname", FilterOperator.EQUAL, petname);
		Query q = new Query("foundcat").setFilter(matches);
		
		PreparedQuery pq = DatastoreInfo.datastore.prepare(q);
		List<Entity> results = pq.asList(FetchOptions.Builder.withLimit(10));
		
		//this gets each of the entities
//		Gson gson = new Gson();
//		String json = new String();
//		for (int i = 0; i < results.size(); i++) {
//		    Entity element = results.get(i);
//		    //json += gson.toJson(element);
//		}
		
		Gson gson = new Gson();
        String json = gson.toJson(results);
        resp.setContentType("application/json");
        resp.getWriter().print(json);
		
//		System.out.println("request: " + req.getReader().readLine());
//		Gson gson = new Gson();
//	  	  
//	  	try {
//            StringBuilder sb = new StringBuilder();
//            String s;
//            while ((s = req.getReader().readLine()) != null) {
//                sb.append(s);
//            }
//            
//            System.out.println("string:" + sb.toString());
// 
//            Cat lostCatInfo = (Cat) gson.fromJson(sb.toString(), Cat.class);
//            System.out.println("lost part");
//            System.out.println(lostCatInfo.petname);
//            
//	  	} catch (Exception ex) {
//            ex.printStackTrace();
//        }
	}	
	
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp){
	  //nothing to post
  }
	

}
