package com.google.appengine.demos.taskqueue;

import java.util.Date;
import java.util.logging.Level;
import com.google.appengine.api.memcache.*;
import java.lang.Object;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

import java.io.IOException;
import java.util.Properties;

// The Worker servlet should be mapped to the "/worker" URL.
public class Worker extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String key = request.getParameter("key");
        String value = request.getParameter("value");
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        
        Entity ourInfo = new Entity("TaskData");
        ourInfo.setProperty("key", key);
        ourInfo.setProperty("value", value);
//        ourInfo.setProperty("date", dateFormat.format(date));
        ourInfo.setProperty("date", date);
        
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        datastore.put(ourInfo);
        
        
        MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
        syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
        
        syncCache.put(key, value); // populate cache
        
    }
}