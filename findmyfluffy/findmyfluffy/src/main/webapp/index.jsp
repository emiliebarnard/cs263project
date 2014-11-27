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
		<form action="/addentry" method="post">
			<p>
			cat's name: <input type="text" name="petname"><br>
			<input type="checkbox" name="chip" value="chip"> microchipped?<br>
			age: <input type="text" name="age"><br>
			sex: <input type="radio" name="sex" value="male" checked>Male <input type="radio" name="sex" value="female">Female<br>
			breed: <select name="breed">
				<option value="dsh">domestic short hair</option>
				<option value="dmh">domestic medium hair</option>
				<option value="dlh">domestic long hair</option>
			</select><br>
			primary color: <select name="color">
				<option value="white">white</option>
				<option value="calico">calico</option>
				<option value="tortoiseshell">tortoiseshell</option>
				<option value="orangetabby">orange tabby</option>
				<option value="browntabby">brown tabby</option>
				<option value="tuxedo">tuxedo</option>
				<option value="black">black</option>
			</select>
			<br>area last seen: <input type="text" name="address"><br>
			your name: <input type="text" name="contactname"><br>
			your e-mail: <input type="text" name="contactemail">
			</p>
		<input type="submit" value="submit">
		</form>
	</div>
	
	<div id="foundForm">
		<h2>found cat?</h2>
		<form action="/submit/found" method="get">
			<p>
			cat's name: <input type="text" name="petname"><br>
			age: <input type="text" name="age"><br>
			sex: <input type="radio" name="sex" value="male" checked>Male <input type="radio" name="sex" value="female">Female<br>
			breed: <select name="breed">
				<option value="volvo">domestic short hair</option>
				<option value="saab">domestic medium hair</option>
				<option value="mercedes">domestic long hair</option>
			</select><br>
			primary color: <select name="color">
				<option value="white">white</option>
				<option value="calico">calico</option>
				<option value="tortoiseshell">tortoiseshell</option>
				<option value="orangetabby">orange tabby</option>
				<option value="browntabby">brown tabby</option>
				<option value="tuxedo">tuxedo</option>
				<option value="black">black</option>
			</select>
			<br>
			area found: <input type="text" name="address"><br>
			your name: <input type="text" name="contactname"><br>
			your e-mail: <input type="text" name="contactemail">
			</p>
		<input type="submit" value="submit">
		</form>
	</div>
</div>

</body>
</html>