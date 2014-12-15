package findmyfluffy.findmyfluffy;

import findmyfluffy.findmyfluffy.Cat;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class MatchLost extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		System.out.println("in MatchLost");
		System.out.println(req.getParameter("n"));
		System.out.println(req.getParameter("chip"));
		System.out.println(req.getParameter("age"));
		System.out.println(req.getParameter("s"));
		System.out.println(req.getParameter("b"));
		System.out.println(req.getParameter("c"));
		System.out.println(req.getParameter("a"));
		System.out.println(req.getParameter("cn"));
		System.out.println(req.getParameter("ce"));
		
		resp.getWriter().println("lost success");
		
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
