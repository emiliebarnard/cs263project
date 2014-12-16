<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <link type="text/css" rel="stylesheet" href="/stylesheets/main.css"/>
<!--     jquery for JSON use: -->
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    
<!--     json stuff -->
    <script type="text/javascript">
    	function ConvertFormToJSON(form){
    		var array = jQuery(form).serializeArray();
    		var json = {};
    		
    		jQuery.each(array, function(){
	    		json[this.name] = this.value || '';
    		});
    		
    		return json;
    	}
    	
    	function submitJSONlostForm(){
/*     		alert("start of submitJSONlostform"); */
    		var lostForm = document.getElementById('lostForm');
    		var jsonInfo = ConvertFormToJSON(lostForm);
/*     		alert(JSON.stringify(jsonInfo)); */
    		 $.ajax({
           		type: "POST",
   				url: "/submit/lost",
   				data: JSON.stringify(jsonInfo),
				cache: false
   				/*
dataType: "json",
				contentType: 'application/json',
        		mimeType: 'application/json'
*/
			}).done(function() {
				/*
findMatchLost(jsonInfo);
				alert("after findMatchLost");
                window.location.href = "/submit/lost/?catname=" + document.getElementById('lostpetname').value ;
*/
                
            }).fail(function(jqXHR, textStatus) {
                alert("AJAX Failed with status " + textStatus);
            });
			
/*  			return false; */
    	}
    	
    	function submitJSONfoundForm(){
    		
    		var lostForm = document.getElementById('foundFormInfo');
    		var jsonInfo = ConvertFormToJSON(lostForm);
    		 $.ajax({
           		type: "POST",
   				url: "/submit/found",
   				data: JSON.stringify(jsonInfo),
				cache: false
   				/*
dataType: "json",
				contentType: 'application/json',
        		mimeType: 'application/json'
*/
			}).done(function() {
                window.location.href = "/submit/found/";
                findMatchFound(jsonInfo);
            }).fail(function(jqXHR, textStatus) {
                alert("AJAX Failed with status " + textStatus);
            });
			
			//return false;
    	}
    	
    	function success(){
	    	//alert("success")
    	}
    	
    	function findMatchLost(){
    		alert("start of findMatchLost");
    		$.get("matches/lost/?n=" + document.getElementById('lostpetname').value + "&chip=" + $('#chip').prop('checked').toString() + "&age=" + document.getElementById('age').value + "&s=" + $('input:radio[name=sex]:checked').val() + "&b=" + document.getElementById('breed').value + "&c=" + document.getElementById('color').value + "&a=" + document.getElementById('area').value + "&cn=" + document.getElementById('contactname').value + "&ce=" +document.getElementById('contactemail').value, function(data) {
				alert( "data: " + data);
				$( "#wrapper" ).empty();
				$( "#wrapper" ).append( "<p>" + data +"</p>" );
			}).
				done(function() {
					//alert( "second success" );
				})
				.fail(function() {
					alert( "Error. Please try again later." );
					})
				.always(function() {
					//alert( "finished" );
			});
    		/* alert(JSON.stringify(jsonInfo)); */
    		/*
alert("in findMatchLost");
    		 $.ajax({
           		type: "GET",
   				url: "/matches/lost/?n=" + document.getElementById('lostpetname').value + "&chip=" + $('#chip').prop('checked').toString() + "&age=" + document.getElementById('age').value + "&s=" + document.getElementById('sex:checked').value + "&b=" + document.getElementById('breed').value + "&c=" + document.getElementById('color').value + "&a=" + document.getElementById('area').value + "&cn=" + document.getElementById('contactname').value + "&ce=" +document.getElementById('contactemail').value,
   				data: JSON.stringify(jsonInfo),
				cache: false,
				success: success,
				dataType: "json"
   				/*
dataType: "json",
				contentType: 'application/json',
        		mimeType: 'application/json'
*/
			/*
}).done(function() {
                alert("done of findMatchLost");
            }).fail(function(jqXHR, textStatus) {
                alert("AJAX Failed with status " + textStatus);
            });
			
			return false;
*/
    	}
    	
		function findMatchFound(jsonInfo){
    		 $.ajax({
           		type: "GET",
   				url: "/matches/found/?n=" + document.getElementById('foundpetname').value + "&chip=" + $('#fchip').prop('checked').toString() + "&age=" + document.getElementById('fage').value + "&s=" + document.getElementById('fsex:checked').value + "&b=" + document.getElementById('fbreed').value + "&c=" + document.getElementById('fcolor').value + "&a=" + document.getElementById('farea').value + "&cn=" + document.getElementById('fcontactname').value + "&ce=" +document.getElementById('fcontactemail').value,
   				data: JSON.stringify(jsonInfo),
				cache: false,
				success: success,
				dataType: "json"
   				/*
dataType: "json",
				contentType: 'application/json',
        		mimeType: 'application/json'
*/
			}).done(function() {
                //put stuff here
            }).fail(function(jqXHR, textStatus) {
                alert("AJAX Failed with status " + textStatus);
            });
			
			return false;
    	}
    	
		$(document).ready(function () {
			$( "#submitlostbutton" ).click(function() {
/*
				alert( "Handler for .click() called." );
				alert("about to call submitJSONlostForm");
*/
				submitJSONlostForm();
/*
				alert("finished calling submitJSONlostForm");
				alert("about to call findMatchLost");
*/
				findMatchLost();
/* 				alert("after calling findMatchLost"); */
				
});
    
		});
	</script>
    
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
		<!-- <form action="/submit/lost" method="post"> -->
<!-- 		<form id="lostFormInfo" onsubmit="submitJSONlostForm()"> -->
<!-- 			<form id="lostFormInfo"> -->
			<p>
			cat's name: <input type="text" name="petname" id="lostpetname"><br>
			<input type="checkbox" name="chip" value="chip" id="chip"> microchipped?<br>
			age: <input type="text" name="age" id="age"><br>
			sex: <input type="radio" id="sex" name="sex" value="male" checked>Male <input type="radio" name="sex" value="female">Female<br>
			breed: <select name="breed" id="breed">
				<option value="dsh">domestic short hair</option>
				<option value="dmh">domestic medium hair</option>
				<option value="dlh">domestic long hair</option>
				<option value="bengal">bengal</option>
				<option value="burmese">burmese</option>
				<option value="himalayan">himalayan</option>
				<option value="lynx">lynx</option>
				<option value="mainecoon">maine coon</option>
				<option value="persian">persian</option>
				<option value="pixiebob">pixie bob</option>
				<option value="russian blue">russian blue</option>
				<option value="tonkinese">tonkinese</option>
				<option value="siamese">siamese</option>
			</select><br>
			primary coloring: <select name="color" id="color">
				<option value="white">white</option>
				<option value="calico">calico</option>
				<option value="tortoiseshell">tortoiseshell</option>
				<option value="orangetabby">orange tabby</option>
				<option value="browntabby">brown tabby</option>
				<option value="greytabby">grey tabby</option>
				<option value="blacktabby">black tabby</option>
				<option value="tuxedo">tuxedo</option>
				<option value="black">black</option>
				<option value="sealpoint">seal point</option>
				<option value="lynx">lynx</option>
				<option value="lynx">grey</option>
			</select>
			<br>area last seen: <input type="text" name="area" id="area"><br>
			your name: <input type="text" name="contactname" id="contactname"><br>
			your phone or e-mail: <input type="text" name="contactemail" id="contactemail">
			</p>
		<button id="submitlostbutton">Submit</button>
<!-- 		</form> -->
	</div>
	
	<div id="foundForm">
		<h2>found cat?</h2>
		<form id="foundFormInfo" onsubmit="return submitJSONfoundForm()">
			<p>
			cat's name: <input type="text" name="petname" id="foundpetname"><br>
			<input type="checkbox" name="chip" value="chip" id="fchip"> microchipped?<br>
			age: <input type="text" name="age" id="fage"><br>
			sex: <input type="radio" id="fsex" name="sex" value="male" checked>Male <input type="radio" name="sex" value="female">Female<br>
			breed: <select name="breed" id="fbreed">
				<option value="dsh">domestic short hair</option>
				<option value="dmh">domestic medium hair</option>
				<option value="dlh">domestic long hair</option>
				<option value="bengal">bengal</option>
				<option value="burmese">burmese</option>
				<option value="himalayan">himalayan</option>
				<option value="lynx">lynx</option>
				<option value="mainecoon">maine coon</option>
				<option value="persian">persian</option>
				<option value="pixiebob">pixie bob</option>
				<option value="russian blue">russian blue</option>
				<option value="tonkinese">tonkinese</option>
				<option value="siamese">siamese</option>
			</select><br>
			primary coloring: <select name="color" id="fcolor">
				<option value="white">white</option>
				<option value="calico">calico</option>
				<option value="tortoiseshell">tortoiseshell</option>
				<option value="orangetabby">orange tabby</option>
				<option value="browntabby">brown tabby</option>
				<option value="greytabby">grey tabby</option>
				<option value="blacktabby">black tabby</option>
				<option value="tuxedo">tuxedo</option>
				<option value="black">black</option>
				<option value="sealpoint">seal point</option>
				<option value="lynx">lynx</option>
				<option value="lynx">grey</option>
			</select>
			<br>area last seen: <input type="text" name="area" id="farea"><br>
			your name: <input type="text" name="contactname" id="fcontactname"><br>
			your phone or e-mail: <input type="text" name="contactemail" id="fcontactemail">

			</p>
		<input type="submit" value="submit">
		</form>
	</div>
</div>

</body>
</html>