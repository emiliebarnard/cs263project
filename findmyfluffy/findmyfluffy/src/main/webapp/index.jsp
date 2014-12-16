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
    
    	function makeSerializable(elem) {
  return $(elem).prop('elements', $('*', elem).andSelf().get());
}

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
    		var jsonInfo = ConvertFormToJSON(makeSerializable(lostForm));
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
    		
    		var foundForm = document.getElementById('foundForm');
    		var jsonInfo = ConvertFormToJSON(makeSerializable(foundForm));
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
                /*
window.location.href = "/submit/found/";
                findMatchFound(jsonInfo);
*/
            }).fail(function(jqXHR, textStatus) {
                alert("AJAX Failed with status " + textStatus);
            });
			
			//return false;
    	}
    	
    	function success(){
	    	//alert("success")
    	}
    	
    	function parseJSONinfo(jsonStringed){
/*     		alert("in parse"); */
    		if (jsonStringed == "[]") {
	    		return "there are no current matches. please try again later! =^.^=";
    		}
    		else{
    			//get rid of brackets on string:
    			jsonStringed = jsonStringed.substring(1, jsonStringed.length-1);
/*     			alert(jsonStringed); */
    			output = "";
    			//first split up the matches:
    			matchesArray = jsonStringed.split('{"key":{"parentKey":');
    			//this gives an empty first element so we'll start at the next one
    			for (var i = 1; i < matchesArray.length; i++) {
					output += '<div id="matchesBox"><p>';
					//for each match, we want to get the name:
					match = matchesArray[i].split('propertyMap":');
					match = match[1];
					properties = match.split(",");
					
					name = properties[7];
					/* output += '<b>cat name</b>: ' + name.substring(0, name.length-1) + '<br>'; */
					output += '<b>cat name</b>: ' + name.substring(11, name.length-1) + '<br>';
					
/*
					chip = properties[8];
					chip = chip.split(':"');
					chip = chip[1];
					output += '<b>microchipped?</b>: ' + chip.substring(0, chip.length-1) + '<br>';
*/
					
					sex = properties[1];
					sex = sex.split(':"');
					sex = sex[1];
					output += '<b>sex</b>: ' + sex.substring(0, sex.length-1) + '<br>';
					
					age = properties[5];
					age = age.split(':"');
					age = age[1];
					output += '<b>age</b>: ' + age.substring(0, age.length-1) + '<br>';
					
					breed = properties[0];
					breed = breed.split(':"');
					breed = breed[1];
					output += '<b>breed</b>: ' + breed.substring(0, breed.length-1) + '<br>';

					color = properties[3];
					color = color.split(':"');
					color = color[1];
					output += '<b>primary color</b>: ' + color.substring(0, color.length-1) + '<br>';
					
					area = properties[2];
					area = area.split(':"');
					area = area[1];
					area = area + "";
					area = area.replace(/(?:\\[rn])+/g, "");
					output += '<b>area found</b>: ' + area.substring(0, area.length-1) + '<br>';
					
					cn = properties[4];
					cn = cn.split(':"');
					cn = cn[1];
					output += '<b>contact name</b>: ' + cn.substring(0, cn.length-1) + '<br>';
					
					ce = properties[6];
					ce = ce.split(':"');
					ce = ce[1];
					ce = ce + "";
					ce = ce.replace(/(?:\\[rn])+/g, "");
					output += '<b>contact phone/email</b>: ' + ce.substring(0, ce.length-1) + '<br>';
								
					output += "<p></div>";

				}
				return output;
    		}
    	}
    	
    	function findMatchLost(){
    		/* alert("start of findMatchLost"); */
    		$.get("matches/lost/?n=" + document.getElementById('lostpetname').value + "&chip=" + $('#chip').prop('checked').toString() + "&age=" + document.getElementById('age').value + "&s=" + $('input:radio[name=sex]:checked').val() + "&b=" + document.getElementById('breed').value + "&c=" + document.getElementById('color').value + "&a=" + document.getElementById('area').value + "&cn=" + document.getElementById('contactname').value + "&ce=" +document.getElementById('contactemail').value, function(data) {
/* 				alert( "data: " + JSON.stringify(data)); */
				$( "#page" ).empty();
				$( "#page" ).append("<h2>potentional matches:</h2>");
				$( "#page" ).append( "<p>" + parseJSONinfo(JSON.stringify(data)) +"</p>" );
				$( "#page" ).append("<h2>general tips for finding a lost cat:</h2>");
				$( "#page" ).append( "<p>most importantly: don't lose hope!<br>alert your local animal shelter<br>post lost cat signs (include a recent photo and contact info)<br>alert your closest neighbors<br>search at night with a flashlight<br>leave point of escape open</p>");
				$( "#page" ).append( '<br><br><p><a href="/">back home</a></p>' );
			}).
				done(function() {
			
				})
				.fail(function() {
					alert( "Error. Please try again later." );
					})
				.always(function() {
					//alert( "finished" );
			});
    	}
    	
		function findMatchFound(jsonInfo){
    		 $.get("matches/found/?n=" + document.getElementById('foundpetname').value + "&chip=" + $('#fchip').prop('checked').toString() + "&age=" + document.getElementById('fage').value + "&s=" + $('input:radio[name=fsex]:checked').val() + "&b=" + document.getElementById('fbreed').value + "&c=" + document.getElementById('fcolor').value + "&a=" + document.getElementById('farea').value + "&cn=" + document.getElementById('fcontactname').value + "&ce=" +document.getElementById('fcontactemail').value, function(data) {
/* 				alert( "data: " + JSON.stringify(data)); */
				$( "#page" ).empty();
				$( "#page" ).append("<h2>potentional matches:</h2>");
				$( "#page" ).append( "<p>" + parseJSONinfo(JSON.stringify(data)) +"</p>" );
				$( "#page" ).append("<h2>general suggestions for found cats:</h2>");
				$( "#page" ).append( "<p>check and see if the cat is microchipped<br>check for any tags/collars<br>check with your neighbors</p>" );
				$( "#page" ).append( '<br><br><p><a href="/">back home</a></p>' );
			}).
				done(function() {
			
				})
				.fail(function() {
					alert( "Error. Please try again later." );
					})
				.always(function() {
					//alert( "finished" );
			});
    	}
    	
		$(document).ready(function () {
			$( "#submitlostbutton" ).click(function() {
			
			
			//only want it to go if everything is filled in
			
			if($('#lostpetname').val() == '') {
				alert("Please enter the cat's name.");
			}
			else if($('#age').val() == '') {
				alert("Please enter the cat's age.");
			}
			
			else if (!$("#sexf").is(":checked") && !$("#sexm").is(":checked")){
				alert("Please select a sex.");
			}
			else if($('#area').val() == '') {
				alert("Please enter the area last seen.");
			}
			else if($('#contactname').val() == '') {
				alert("Please your name.");
			}
			else if($('#contactemail').val() == '') {
				alert("Please your phone or e-mail.");
			}
			else{ //if everything is filled in, then we can submit all the things
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
		/* 		alert("after calling findMatchLost"); */
		}
				
});
		$( "#submitfoundbutton" ).click(function() {
		
		//only want it to go if everything is filled in
			if($('#foundpetname').val() == '') {
				alert("Please enter the cat's name.");
			}
			else if($('#fage').val() == '') {
				alert("Please enter the cat's age.");
			}
			else if (!$("#fsexf").is(":checked") && !$("#fsexm").is(":checked")){
				alert("Please select a sex.");
			}
			else if($('#farea').val() == '') {
				alert("Please enter the area last seen.");
			}
			else if($('#fcontactname').val() == '') {
				alert("Please your name.");
			}
			else if($('#fcontactemail').val() == '') {
				alert("Please your phone or e-mail.");
			}
			else{ //if everything is filled in, then we can submit all the things
				/*
alert( "Handler for .click() called." );
				alert("about to call submitJSONlostForm");
*/
				submitJSONfoundForm();
				/*
alert("finished calling submitJSONlostForm");
				alert("about to call findMatchLost");
*/
				findMatchFound();
		/* 		alert("after calling findMatchLost"); */
		}
				
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
<div id="page">
<div id="wrapper">
	<div id="lostForm">
		<h2>lost cat?</h2>
		<!-- <form id="lostFormInfo"> -->
		<!-- <form action="/submit/lost" method="post"> -->
<!-- 		<form id="lostFormInfo" onsubmit="submitJSONlostForm()"> -->
<!-- 			<form id="lostFormInfo"> -->
			<p>
			cat's name: <input type="text" name="petname" id="lostpetname"><br>
			<input type="checkbox" name="chip" value="chip" id="chip"> microchipped?<br>
			age: <input type="text" name="age" id="age"><br>
			sex: <input type="radio" id="sexm" name="sex" value="male">male <input type="radio" name="sex" id="sexf" value="female">female<br>
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
			<p>
			cat's name: <input type="text" name="petname" id="foundpetname"><br>
			<input type="checkbox" name="chip" value="chip" id="fchip"> microchipped?<br>
			age: <input type="text" name="age" id="fage"><br>
			sex: <input type="radio" id="fsexm" name="sex" value="m">male <input type="radio" name="sex" id="fsexf" value="f">female<br>
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
			<button id="submitfoundbutton">Submit</button>
	</div>
</div>
</div>

</body>
</html>