package findmyfluffy.findmyfluffy;

import findmyfluffy.findmyfluffy.Cat;

import com.google.gson.Gson;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;

import static com.google.appengine.api.taskqueue.TaskOptions.Builder.*;

import java.io.IOException;

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
	  
	  //GSON Conversion:
	  	  Gson gson = new Gson();
	  	  
	  	try {
            StringBuilder sb = new StringBuilder();
            String s;
            while ((s = req.getReader().readLine()) != null) {
                sb.append(s);
            }
 
            Cat lostCatInfo = (Cat) gson.fromJson(sb.toString(), Cat.class);
            
            String lostCatName = lostCatInfo.petname;
            //set up chip info:
      	   	String chipped = "false";
              	if (lostCatInfo.chip != null && !lostCatInfo.chip.isEmpty()) {
              		if (lostCatInfo.chip.equals("chip")){
                  		  chipped = "true";
              		}
              	}
            //set up sex info:
             if (lostCatInfo.sex.equals("m")){
                	lostCatInfo.sex = "male";
             }
             if (lostCatInfo.sex.equals("f")){
                	lostCatInfo.sex = "female";
             }   
      
        //Task Queue
        Queue queue = QueueFactory.getDefaultQueue();
        queue.add(withUrl("/lostcatadder").param("name", lostCatName).param("chip", chipped).param("age", lostCatInfo.age).param("sex", lostCatInfo.sex).param("breed", lostCatInfo.breed).param("color", lostCatInfo.color).param("area", lostCatInfo.area).param("contactname", lostCatInfo.contactname).param("contactemail", lostCatInfo.contactemail));
       
        } catch (Exception ex) {
            ex.printStackTrace();
        }

  }
}