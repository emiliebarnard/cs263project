package findmyfluffy.findmyfluffy;

import static com.google.appengine.api.taskqueue.TaskOptions.Builder.withUrl;
import findmyfluffy.findmyfluffy.Cat;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;

import com.google.gson.Gson;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * AddFoundEntryServlet Class
 * 
 * This class is a servlet run behind the scenes. It calls a task queue to add a found cat entity into the datastore.
 * 
 * @author emilie (Emilie Menard Barnard) - <a href="mailto:emilie@cs.ucsb.edu">emilie@cs.ucsb.edu</a>
 * @version 1.0
 * 
 */
public class AddFoundEntryServlet extends HttpServlet {
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
			// no get used
	}	
	
  /* (non-Javadoc)
 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
 */
@Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {

	  //GSON conversion:
	  	Gson gson = new Gson();
	  	  
	  	try {
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = req.getReader().readLine()) != null) {
                sb.append(s);
            }
 
            Cat foundCatInfo = (Cat) gson.fromJson(sb.toString(), Cat.class);
            
            //GSON has been converted

      	  String foundCatName = foundCatInfo.petname;
      	  //set up chipped info:
      	  String chipped = "false";
      	  if (foundCatInfo.chip != null && !foundCatInfo.chip.isEmpty()) {
      		if (foundCatInfo.chip.equals("chip")){
          		  chipped = "true";
      		}
      	  }
      	  //set up sex info:
      	  if (foundCatInfo.sex.equals("m")){
        	foundCatInfo.sex = "male";
      	  }
      	  if (foundCatInfo.sex.equals("f")){
        	foundCatInfo.sex = "female";
      	  }
      	  
      	//TaskQueue:
      	Queue queue = QueueFactory.getDefaultQueue();
      	queue.add(withUrl("/foundcatadder").param("name", foundCatName).param("chip", chipped).param("age", foundCatInfo.age).param("sex", foundCatInfo.sex).param("breed", foundCatInfo.breed).param("color", foundCatInfo.color).param("area", foundCatInfo.area).param("contactname", foundCatInfo.contactname).param("contactemail", foundCatInfo.contactemail));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
  }
}