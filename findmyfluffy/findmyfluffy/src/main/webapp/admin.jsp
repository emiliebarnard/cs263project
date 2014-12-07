<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>

<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>


<html>
    <head>
        <title>Upload New Data File</title>
        <link type="text/css" rel="stylesheet" href="/stylesheets/main.css"/>
    </head>
    <body>
    <h1>find my fluffy admin page</h1>
	<p>an online database matching lost and found cats</p>
	<p>use this page to upload csv files of lost and found cats</p>
    
    <div id="wrapper">
       	<div id="lostForm">
       	<h2>lost cats csv file upload</h2>
        <form action="<%= blobstoreService.createUploadUrl("/upload") %>" method="post" enctype="multipart/form-data">
            <input type="text" name="foo">
            <input type="file" name="myFile">
            <input type="submit" value="Submit">
        </form>
    	</div>
    	
    	<div id="foundForm">
    	<h2>found cats csv file upload</h2>
        <form action="<%= blobstoreService.createUploadUrl("/upload") %>" method="post" enctype="multipart/form-data">
            <input type="text" name="foo">
            <input type="file" name="myFile">
            <input type="submit" value="Submit">
        </form>
    	</div>
    </div>	
    </body>
</html>