<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User</title>
<script src="component/jquery/jquery.min.js"></script>
<link rel="stylesheet" href="component/bootstrap/css/bootstrap.min.css" />
<script src="component/DataTables/datatables.min.js"  type="text/javascript"></script>
<link rel="stylesheet" href="component/DataTables/datatables.min.css" />
<script type="text/javascript">

$( document ).ready(function() {
	$(document).delegate('#cancel', 'click', function(event) {			
		event.preventDefault();
		var a = "";
		console.log(a);
		 $("#name").val(a);
		});
	});


$( document ).ready(function() {
	$(document).delegate('#send', 'click', function(event) {			
		event.preventDefault();
		
	      var roleRequest = {
					name : $("#name").val()
			      };
		 
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "http://localhost:8080/createRole",
			data: JSON.stringify(roleRequest),
			cache: false,
			success: function(data) {
				$("#msg").html( "<span style='color: green'>Role added successfully</span>" );
				
			},
			error: function(err) {
				$("#msg").html( "<span style='color: red'>Name is required</span>" );
			}
		});
	});

});
</script>
</head>
<body>
<h1>Gestion des Employés</h1>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="/add">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/showAllEmplByProject">Listes</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/addUser">Créer user</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/addRole">Créer les Roles</a>
      </li>
    </ul>
  </div>
</nav>
<h3>Créer un role</h3>
	<form action="#" method="post">
		<table cellpadding="2" cellspacing="2">
			<tr>
				<td>Nom :</td>
				<td><input type="text" name="name" id="name"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Save" id="send"></td>
				<td><button id="cancel">CANCEL</button>
			</tr>
		</table>
	</form>
	<div id="msg"></div>
</body>
</html>