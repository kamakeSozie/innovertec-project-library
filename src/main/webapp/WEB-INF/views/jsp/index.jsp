<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajoutons un nouveau EMPLOYE</title>
<script src="component/component/jquery/jquery.min.js"></script>

	<!-- jQuery Modal -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
<link rel="stylesheet" href="component/component/bootstrap/css/bootstrap.min.css" />
<script type="text/javascript">

$( document ).ready(function() {
	$(document).delegate('#cancel', 'click', function(event) {			
		event.preventDefault();
		var a = "";
		console.log(a);
		 $("#name").val(a);
         $("#email").val(a);
		});
	});


	$( document ).ready(function() {
		$(document).delegate('#send', 'click', function(event) {			
			event.preventDefault();

			
			 var formEmploye = {
					name : $("#name").val(),
			        email : $("#email").val(),
			        roleEntity : $("#roleEntity").val()
			      };
		      
			 
			$.ajax({
				type: "POST",
				//contentType: "application/json",
				url: "http://localhost:8080/createUser",
				data: JSON.stringify(formEmploye),
				cache: false,
				success: function(data) {
					console.log(data);
					$("#msg").html( "<span style='color: green'>Employe added successfully</span>" );
					//window.setTimeout(function(){location.reload()},1000)
					//window.location = "http://localhost:8080/showAll";
					var admin = "ADMIN";
					var user = "USER"
					if (formEmploye.roleEntity == admin) {
						//window.location.href = "/dptView";

						console.log("Admin");
                } else if (formEmploye.roleEntity == user){
                	//window.location.href = "/showAll";
                	console.log("User");
                    }
					
				},
				error: function(err) {
					$("#msg").html( "<span style='color: red'>Name or email is required</span>" );
				}
			});
		});

	});
</script>
</head>
<body>
	<h2>Ajoutons un nouveau EMPLOYE</h2><!-- action="addEmploye" -->
	
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
        <a class="nav-link" href="/showAllDpt">Listes</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/dptView">Département</a>
      </li>
    </ul>
  </div>
</nav>
	<form method="post"><br>
		Name : <input type="text" name="name" id="name"><br>
		Email : <input type="text" name="email" id="email"><br>
		Role: 
			<select id="roleEntity" name="roleEntity">
				<option>--Select Role--</option>
					<c:forEach items="${ listRole }" var="role">
						<option value="${role.name}">${role.name}</option>
							</c:forEach>
			</select>
		<input type="submit" class="btn btn-primary" id="send" value="SEND"/>
		<input type="submit" class="btn btn-primary" id="cancel" value="CANCEL"/>
	</form>
	<div id="msg"></div>
</body>
</html>