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
    for (Entity result : pKeyValue.asIterable()) {   
        String value = (String) result.getProperty("value");   
        String date = (String) result.getProperty("date");   
        String key = (String) result.getProperty("key");
        pageContext.setAttribute("key", key);
    	pageContext.setAttribute("date", date);
    	pageContext.setAttribute("value", value); 
    }   
%>
<p>The value in ${fn:escapeXml(keyname)} is ${fn:escapeXml(value)}! =D

</body>
</html>