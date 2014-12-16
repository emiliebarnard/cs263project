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
       	<p>files should be of the following form:<br>
       	area lost, breed, color, additional details (including chip info), sex, age, catname, contact name, contact info
       	</p>
        <form action="<%= blobstoreService.createUploadUrl("/uploadlost") %>" method="post" enctype="multipart/form-data">
            <input type="text" name="foo">
            <input type="file" name="myFile">
            <input type="submit" value="Submit">
        </form>
    	</div>
    	
    	<div id="foundForm">
    	<h2>found cats csv file upload</h2>
    	<p>files should be of the following form:<br>
    	cat name, sex, age, description (breed & color), chipped? (true/false), contact name, contact info, area found
    	</p>
        <form action="<%= blobstoreService.createUploadUrl("/uploadfound") %>" method="post" enctype="multipart/form-data">
            <input type="text" name="foo">
            <input type="file" name="myFile">
            <input type="submit" value="Submit">
        </form>
    	</div>
    </div>
    <br><br><p><a href="/">back home</a></p>	
    </body>
</html>