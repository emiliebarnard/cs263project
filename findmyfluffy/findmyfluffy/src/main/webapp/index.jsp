<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="/stylesheets/main.css"/>
</head>

<body>

<!-- This was the guestbook demo: -->
<!--
<%
    String guestbookName = request.getParameter("guestbookName");
    if (guestbookName == null) {
        guestbookName = "default";
    }
    pageContext.setAttribute("guestbookName", guestbookName);
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();
    if (user != null) {
        pageContext.setAttribute("user", user);
%>

<p>Hello, ${fn:escapeXml(user.nickname)}! (You can
    <a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">sign out</a>.)</p>
<%
} else {
%>
<p>Hello!
    <a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Sign in</a>
    to include your name with greetings you post.</p>
<%
    }
%>
-->
<h1>find my fluffy</h1>
<p>an online database matching lost and found cats</p>

<div id="wrapper">
	<div id="lostForm">
		<h2>lost cat?</h2>
		<form action="/submit/lost" method="get">
			<p>
			cat's name: <input type="text" name="petname"><br>
			your name: <input type="text" name="contactname"><br>
			</p>
		<input type="submit" value="submit">
		</form>
	</div>
	
	<div id="foundForm">
		<h2>found cat?</h2>
		<form action="/submit/found" method="get">
			<p>
			cat's name: <input type="text" name="petname"><br>
			your name: <input type="text" name="contactname"><br>
			</p>
		<input type="submit" value="submit">
		</form>
	</div>
</div>

</body>
</html>