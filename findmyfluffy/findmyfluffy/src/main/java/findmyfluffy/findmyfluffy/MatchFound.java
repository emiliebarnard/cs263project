package findmyfluffy.findmyfluffy;

import findmyfluffy.findmyfluffy.Cat;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.gson.Gson;

public class MatchFound extends HttpServlet {
	
	public void findMatches(Cat lostCatInfo){
		
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		String petname = req.getParameter("n").toLowerCase();
		String chip = req.getParameter("chip");
		String age = req.getParameter("age");
		String sex = req.getParameter("s");
		String breed = req.getParameter("b");
		String color = req.getParameter("c");
		String area = req.getParameter("a");
		String contactname = req.getParameter("cn");
		String contactemail = req.getParameter("ce");
		
      	if (sex.equals("m")){
        	sex = "male";
        }
        
        if (sex.equals("f")){
        	sex = "female";
        }
		
		//now we need to search the datastore for matches

		Filter nameFilter = new FilterPredicate("catname", FilterOperator.EQUAL, petname);
		Filter ageFilter = new FilterPredicate("age", FilterOperator.EQUAL, age);
		Filter sexFilter = new FilterPredicate("sex", FilterOperator.EQUAL, sex);
		Filter breedFilter = new FilterPredicate("breed", FilterOperator.EQUAL, breed);
		Filter colorFilter = new FilterPredicate("color", FilterOperator.EQUAL, color);
		Filter areaFilter = new FilterPredicate("area", FilterOperator.EQUAL, area);
		
		
		Filter nasFilter = CompositeFilterOperator.and(nameFilter, ageFilter, sexFilter);
		Filter nsbFilter = CompositeFilterOperator.and(nameFilter, sexFilter, breedFilter);
		Filter nbcFilter = CompositeFilterOperator.and(nameFilter, breedFilter, colorFilter);
		Filter ncaFilter = CompositeFilterOperator.and(nameFilter, colorFilter, areaFilter);
		Filter nabFilter = CompositeFilterOperator.and(nameFilter, ageFilter, breedFilter);
		Filter nacFilter = CompositeFilterOperator.and(nameFilter, ageFilter, colorFilter);
		Filter naaFilter = CompositeFilterOperator.and(nameFilter, ageFilter, areaFilter);
		Filter nscFilter = CompositeFilterOperator.and(nameFilter, sexFilter, colorFilter);
		Filter nsaFilter = CompositeFilterOperator.and(nameFilter, sexFilter, areaFilter);
		Filter nbaFilter = CompositeFilterOperator.and(nameFilter, breedFilter, areaFilter);
		Filter asbFilter = CompositeFilterOperator.and(ageFilter, sexFilter, breedFilter);
		Filter abcFilter = CompositeFilterOperator.and(ageFilter, breedFilter, colorFilter);
		Filter acaFilter = CompositeFilterOperator.and(ageFilter, colorFilter, areaFilter);
		Filter abaFilter = CompositeFilterOperator.and(ageFilter, breedFilter, areaFilter);
		Filter asaFilter = CompositeFilterOperator.and(ageFilter, sexFilter, areaFilter);
		Filter ascFilter = CompositeFilterOperator.and(ageFilter, sexFilter, colorFilter);
		Filter sbcFilter = CompositeFilterOperator.and(sexFilter, breedFilter, colorFilter);
		Filter sbaFilter = CompositeFilterOperator.and(sexFilter, breedFilter, areaFilter);
		Filter bcaFilter = CompositeFilterOperator.and(breedFilter, colorFilter, areaFilter);
		
		Filter anyThree = CompositeFilterOperator.or(nasFilter, nsbFilter, nbcFilter, ncaFilter, nabFilter, nacFilter, naaFilter, nscFilter, nsaFilter, nbaFilter, asbFilter, abcFilter, acaFilter, abaFilter, asaFilter, ascFilter, sbcFilter, sbaFilter, bcaFilter);
		
		//Future work: should this be changed to something else?
		
		Query q = new Query("lostcat").setFilter(anyThree);
		
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
	}	
	
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp){
	  //nothing to post
  }
	

}
