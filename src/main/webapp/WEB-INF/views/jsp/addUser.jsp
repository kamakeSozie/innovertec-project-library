<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Utilisateur</title>
<script src="component/jquery/jquery.min.js"></script>
<link rel="stylesheet" href="component/bootstrap/css/bootstrap.min.css" />
<script src="component/DataTables/datatables.min.js"  type="text/javascript"></script>
<link rel="stylesheet" href="component/DataTables/datatables.min.css" />
<script type="text/javascript">

$( document ).ready(function() {
	$(document).delegate('#cancel', 'click', function(event) {			
		event.preventDefault();
		var a = "";
		 $("#name").val(a);
         $("#email").val(a);
         $("#password").val(a);
		});
	});


$( document ).ready(function() {
	$(document).delegate('#send', 'click', function(event) {			
		event.preventDefault();

		var listrole = [];

		/* var roleEntity = [{
				name : $("#roleEntity").val()
				}
				] */
		
		 var userRequest = {
				name : $("#name").val(),
		        email : $("#email").val(),
		        password : $("#password").val(),
		        roleEntity : $("#roleEntity").val()
		      };
		 
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "http://localhost:8080/createUser",
			data: JSON.stringify(userRequest),
			cache: false,
			success: function(data) {
				console.log(data);
				$("#msg").html( "<div class='alert alert-success'><strong>Success!</strong>User added successfully</div>" );
				
				window.setTimeout(function(){location.reload()},1000)
				var admin = "ADMIN";
				var user = "USER"
				if (userRequest.roleEntity == admin) {
					//window.location.href = "/dptView";
					alert("Admin connected");
            } else if (userRequest.roleEntity == user){
            	//window.location.href = "/showAll";
            		alert("User connected");
                }
				
			},
			error: function(error) {
$("#msg").html( "<div class='alert alert-danger'><strong>Error! </strong>"+error.responseJSON.message+"</div>" );
			
			}
		});
	});

});

//Add role implementation
	$( document ).ready(function() {
	$(document).delegate('#cancelRole', 'click', function(event) {			
		event.preventDefault();
		var a = "";
		console.log(a);
		 $("#name").val(a);
		});
	});


$( document ).ready(function() {
	$(document).delegate('#sendRole', 'click', function(event) {			
		event.preventDefault();
		
	      var roleRequest = {
					name : $("#nameRole").val()
			      };
		 
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "http://localhost:8080/createRole",
			data: JSON.stringify(roleRequest),
			cache: false,
			success: function(data) {
				$("#msg").html( "<div class='alert alert-success'><strong>Success!</strong>Role added successfully</div>" );
				window.setTimeout(function(){location.reload()},1000)
				
			},
			error: function(error) {
				$("#msg").html( "<div class='alert alert-danger'><strong>Error! </strong>"+error.responseJSON.message+"</div>" );
							
							}
		});
	});

});
//End add role

//Add project implementation
	$( document ).ready(function() {
	$(document).delegate('#sendProjectToUser', 'click', function(event) {			
		event.preventDefault();
		var a = "";
		console.log(a);
		 $("#name").val(a);
		});
	});


$( document ).ready(function() {
	$(document).delegate('#sendProjectToUser', 'click', function(event) {			
		event.preventDefault();
		
	     var userId = $("#userId").val();
		 var projetid = $("#projetId").val();

			  
		 
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "http://localhost:8080/userToProjet?userId="+userId+"&projetid="+projetid,
			data: JSON.stringify({
	    		  userId : $("#userId").val(),
	    		  projetid : $("#projetId").val()
			      }),
			cache: false,
			success: function(data) {
				$("#msg").html( "<div class='alert alert-success'><strong>Success!</strong>Added successfully</div>" );
				window.setTimeout(function(){location.reload()},1000)
				
			},
			error: function(error) {
				$("#msg").html( "<div class='alert alert-danger'><strong>Error! </strong>"+error.responseJSON.message+"</div>" );
							
							}
		});
	});

});
//End add Projet

//Add projet to user
$( document ).ready(function() {
	$(document).delegate('#sendProject', 'click', function(event) {			
		event.preventDefault();
		
	      var projetRequest = {
	    		  projetName : $("#projetName").val(),
	    		  description : $("#description").val()
			      };
		 
		$.ajax({
			type: "POST",
			contentType: "application/json",
			url: "http://localhost:8080/createProjet",
			data: JSON.stringify(projetRequest),
			cache: false,
			success: function(data) {
$("#msg").html( "<div class='alert alert-success'><strong>Success!</strong>Projet added successfully</div>" );
				window.setTimeout(function(){location.reload()},1000)
				
			},
			error: function(error) {
				$("#msg").html( "<div class='alert alert-danger'><strong>Error! </strong>"+error.responseJSON.message+"</div>" );
							
							}
		});
	});

});
//End add projet to use
</script>
</head>
<body>
<h1 class="text-center">Gestion des Employés</h1>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">
    <img src="/image/3d.svg" width="40" height="40" alt="">
  </a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
      <li class="nav-item active">
        <a class="nav-link" href="/adminPages">Accueil <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/showAllEmplByProject">Listes</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/addUser">Tâche administratif</a>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0">
    	<a class="nav-link" href="#"><u>${ username } en ligne</u></a>
      <a class="nav-link" href="/logout"><b>Se Déconnecter </b><span class="sr-only"></span></a>
    </form>
  </div>
</nav>
<div id="msg"></div>

	<div class="container">
		<div class="row">
  			<div class="col-sm-6">
    			<div class="card alert alert-secondary">
      			<div class="card-body">
        			<h5 class="card-title text-center"><u>Créer un utilisateur</u></h5>
        			<form action="#" method="post">
        				<div class="row">
    					<div class="form-group col-md-6">
      						<label for="name">Nom</label>
      						<input type="text" class="form-control" name="name" id="name" placeholder="Entrer votre nom" required="required">
    					</div>
    					<div class="form-group col-md-6">
      						<label for="email">Email</label>
      						<input type="email" class="form-control" name="email" id="email" placeholder="Entrer votre email" required="required">
    					</div>
    					<div class="form-group col-md-6">
      						<label for="password">Mot de passe</label>
      						<input type="password" class="form-control" name="password" id="password" 
      							placeholder="Entre votre mot de passe" required="required">
    					</div>
    					<div class="form-group col-md-6">
      						<label for="roleEntity">Role</label>
      						<select id="roleEntity" name="roleEntity" class="form-control" required="required">
								<option>--Selectionner le role--</option>
								<c:forEach items="${ listRole }" var="role">
									<option value="${role.roleId}">${role.name}</option>
								</c:forEach>
							</select>
    					</div>
    					<div class="form-group col-md-6 mt-2">
    						<button class="btn btn-primary col-md-12" id="cancel">EFFACER</button>
    					</div>
    					<div class="form-group col-md-6 mt-2">
    						<button class="btn btn-primary col-md-12" type="submit" id="send">ENREGISTRER</button>
    					</div>
  						</div>
        			</form>
      			</div>
    			</div>
  			</div>
  			<div class="col-sm-6">
    			<div class="card alert alert-secondary">
      			<div class="card-body">
        			<h5 class="card-title text-center"><u>Créer un projet</u></h5>
        			 <div class="form-row">
    					<div class="form-group col-md-12">
      						<label for="projetName">Nom du projet</label>
      						<input type="text" class="form-control" id="projetName" name="projetName" placeholder="Entrer le nom du projet">
    					</div>
    					<div class="form-group col-md-12">
      						<label for="description">Description du projet</label>
    						<textarea rows="2" cols="" class="form-control" name="description" placeholder="Entrer la description" id="description"></textarea>
    					</div>
    						<button class="btn btn-primary col-md-6 ml-auto mr-auto" type="submit" id="sendProject">
    							ENREGISTRER
    						</button>
  					</div>
      			</div>
    			</div>
  			</div>
		</div>
		<div class="row mt-3">
  			<div class="col-sm-6">
    			<div class="card alert alert-secondary">
      			<div class="card-body">
        			<h4 class="card-title text-center"><u>Créer un role</u></h4>
        			 <div class="form-row">
    					<div class="form-group col-md-12">
      						<label for="nameRole">Nom</label>
      						<input type="text" class="form-control" name="name" id="nameRole" 
      							placeholder="Entrer le nom">
    					</div>
  					<button class="btn btn-primary col-md-6 ml-auto mr-auto" type="submit" id="sendRole">
    					ENREGISTRER
    				</button>
  					</div>
      			</div>
    			</div>
  			</div>
  			<div class="col-sm-6">
    			<div class="card alert alert-secondary">
      			<div class="card-body">
        			<h5 class="card-title text-center"><u>Ajouter un employé à un projet</u></h5>
        			 <div class="form-row">
    					<div class="form-group col-md-6">
      						<label for="userId">Utilisateur</label>
      						<select id="userId" name="userId" class="form-control" required="required">
								<option value="">--Selectionner l'employé--</option>
								<c:forEach items="${ listEmploye }" var="users">
									<option value="${users.userId}">${users.name}</option>
								</c:forEach>
							</select>
    					</div>
    					<div class="form-group col-md-6">
      						<label for="projetId">Projet</label>
      						<select id="projetId" name="projetid" class="form-control" required="required">
								<option value="">--Selectionner le projet--</option>
								<c:forEach items="${ listProject }" var="projects">
									<option value="${projects.projetId}">${projects.projetName}</option>
								</c:forEach>
							</select>
    					</div>
  					<button class="btn btn-primary col-md-6 ml-auto mr-auto" type="submit" id="sendProjectToUser">
    					ENREGISTRER
    				</button>
  					</div>
      			</div>
    			</div>
  			</div>
		</div>
	</div>
</body>
</html>