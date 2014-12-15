package findmyfluffy.findmyfluffy;

import findmyfluffy.findmyfluffy.Cat;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class MatchFound extends HttpServlet {
	
	public void findMatches(Cat lostCatInfo){
		
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		System.out.println("in MatchFound");
		System.out.println(req.getParameter("n"));
		System.out.println(req.getParameter("chip"));
		System.out.println(req.getParameter("age"));
		System.out.println(req.getParameter("s"));
		System.out.println(req.getParameter("b"));
		System.out.println(req.getParameter("c"));
		System.out.println(req.getParameter("a"));
		System.out.println(req.getParameter("cn"));
		System.out.println(req.getParameter("ce"));
		
		
		resp.getWriter().println("found success");
	}	
	
  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp){
	  //nothing to post
  }
	

}
