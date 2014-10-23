package com.google.appengine.demos.taskqueue;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;

import com.google.appengine.api.memcache.*;

import java.lang.Object;

// The ReadData servlet should be mapped to the "/test" URL.
public class ReadData extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    	
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head>");
    out.println("<title>All the Stuffs</title>");
    out.println("</head>");
    out.println("<body bgcolor=\"white\">");
    Query q =  new Query();
    PreparedQuery pq = datastore.prepare(q);
    for (Entity result : pq.asIterable()) {
    	String key = (String) result.getProperty("key"); 
        String value = (String) result.getProperty("value");
        String date = "";
        if (result.getProperty("date")!=null){
        	date = result.getProperty("date").toString();
        }
        else {
        	date = "";
        }  
        out.println("<b>key</b>: "+ key + " <b>value</b>: " + value + " <b>date</b>: " + date + "<br>"); 
    }
    out.println("</body>");
    out.println("</html>");
    }
}