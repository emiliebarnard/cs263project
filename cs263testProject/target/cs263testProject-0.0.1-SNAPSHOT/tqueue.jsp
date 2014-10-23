<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreService" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<%@ page import="com.google.appengine.api.datastore.FetchOptions" %>
<%@ page import="com.google.appengine.api.datastore.Key" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>
<%@ page import="com.google.appengine.api.datastore.Query" %>
<%@ page import="com.google.appengine.api.datastore.Query.FilterOperator" %>
<%@ page import="com.google.appengine.api.datastore.Query.SortDirection" %>
<%@ page import="com.google.appengine.api.datastore.PreparedQuery" %>
<%@ page import="java.util.logging.Level" %>
<%@ page import="com.google.appengine.api.memcache.*" %>
<%@ page import="java.lang.Object" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="/stylesheets/main.css"/>
</head>

<body>
<%
    String keyname = request.getParameter("key");
    if (keyname == null) {
        keyname = "default";
    }
    
    pageContext.setAttribute("keyname", keyname);  
    
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Query keyValue =  new Query("TaskData");
    keyValue.addFilter("key", FilterOperator.EQUAL, keyname);
    keyValue.addSort("date", SortDirection.ASCENDING);
    PreparedQuery pKeyValue = datastore.prepare(keyValue);
    
    MemcacheService syncCache = MemcacheServiceFactory.getMemcacheService();
    syncCache.setErrorHandler(ErrorHandlers.getConsistentLogAndContinue(Level.INFO));
    
    for (Entity result : pKeyValue.asIterable()) {   
        String key = (String) result.getProperty("key");   
        String date = result.getProperty("date").toString();
        pageContext.setAttribute("key", key);
    	pageContext.setAttribute("date", date);
    	
    	String value = "";
    	String cacheUse = "";
    	if (syncCache.get(key)==null){
    		value = null;
    		cacheUse = "?";
    	}
    	else{
    		value = syncCache.get(key).toString(); //read value from cache and convert to string
    		cacheUse = "yes";
    	}
    	
    	if (value == null){   
        	value = (String) result.getProperty("value");
        	cacheUse = "no";
        }
    	pageContext.setAttribute("value", value);
    	pageContext.setAttribute("cacheUse", cacheUse); 
    }
    Stats ourStats = syncCache.getStatistics();
    long numItems = ourStats.getItemCount();
    int accessTime = ourStats.getMaxTimeWithoutAccess();
    pageContext.setAttribute("numItems", numItems);
    pageContext.setAttribute("accessTime", accessTime); 
    
    
       
%>
<p>The value in ${fn:escapeXml(keyname)} is ${fn:escapeXml(value)}! =D
<br>Used cache? ${fn:escapeXml(cacheUse)}
<br>There are currently ${fn:escapeXml(numItems)} items active in the cache, and the last access of least-recently-used live entry occured ${fn:escapeXml(accessTime)} ms ago.</p>

</body>
</html>