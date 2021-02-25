<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Result page</title>
<script src="component/jquery/jquery.min.js"></script>
<link rel="stylesheet" href="component/bootstrap/css/bootstrap.min.css" />
<script src="component/DataTables/datatables.min.js"  type="text/javascript"></script>
<link rel="stylesheet" href="component/DataTables/datatables.min.css" />
<script type="text/javascript">
$( document ).ready(function() {
		$("#SelectedEmpId").change(function(){
			var empTemp = $("#SelectedEmpId :selected").val();
			empTemp=empTemp.split("&");
			var timeOutId = 0;
			if(empTemp != "NONE") {
				$('#showHide').load(' #showHide');
				var idProject=empTemp[1];
			    $.ajax({
				      type : "GET",
				      url :"http://localhost:8080/userListByProjet?projetName="+idProject,
				      setTimeout: false,
				      success: function(result){	
				    	  $(document).ready( function () {
				    		    $('#tableId').DataTable();
				    		} );			          
				         $.each(result, function(i, employe){
				        	  var employes = '<tr>' +
				              '<td>' + employe.id + '</td>' +
				              '<td>' + employe.name.toUpperCase() + '</td>' +
				              '<td>' + employe.email + '</td>' +
				              '<td>' + employe.projetEntities[0].projetName + '</td>' +
				              '</tr>';

				            $('#tableId tbody').append( employes );
				            
				            $('#resultGetAllCustomerDiv').html("Nom projet : "+employe.projetEntities[0].projetName+"</br>Description du projet : "+employe.projetEntities[0].description);
				              });
				          // just re-css for result-div
				          $('#resultGetAllCustomerDiv').css({'background-color':'#D16953', 'color':'white', 'padding':'20px 20px 5px 30px'});
				          
				      },
				      error : function(e) {
				        $("#getResultDiv").html("<strong>Error</strong>");
				        console.log("ERROR: ", e);
				      }
				    });
			    $("#SelectedEmpId").prop('disabled', false);
			}
			
		});
	})
	
	
	$(document).ready( function () {
				    		    $('#tablesId').DataTable();
				    		} );

$(document).ready( function () {
    $('#tableId').DataTable();
} );
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
	<div class="container-fluid">
		<div class="row mt-4">
  			<div class="col-sm-6">
  			
    			<div class="card col-sm-12">
    			<span class="text-white bg-info text-center">
    				<h4>Liste des Utilisateurs</h4>
    			</span>
      			<div class="card-body">
        				
	<table class="table table-striped col-sm-12" id="tableId">
  		<thead>
    		<tr>
      			<th scope="col">#</th>
      			<th scope="col">Nom</th>
      			<th scope="col">Email</th>
      			<th scope="col">Project</th>
    		</tr>
  		</thead>
  		<tbody>
  			<c:forEach items="${ listEmploye }" var="employe">
   				 <tr>
   				 	<td><c:out value="${ employe.id }"/></td>
    	  			<td><c:out value="${ employe.name }"/></td>
      				<td><c:out value="${ employe.email }"/></td>
      				<td><c:out value="${ employe.projetEntities[0].projetName }"/></td>
    			</tr>
   			</c:forEach>
 		</tbody>
	</table>
      			</div>
    			</div>
  			</div>
  			<div class="col-sm-6">
    			<div class="card col-sm-12">
    			<span class="text-white bg-info text-center">
    				<h4>Liste des Projets</h4>
    			</span>
      			<div class="card-body">
	<table class="table table-striped col-sm-12" id="tablesId">
  		<thead>
    		<tr>
      			<th scope="col">#</th>
      			<th scope="col">Nom projet</th>
      			<th scope="col">Description</th>
    		</tr>
  		</thead>
  		<tbody>
  			<c:forEach items="${ listProject }" var="project">
   				 <tr>
   				 	<td><c:out value="${ project.id }"/></td>
    	  			<td><c:out value="${ project.projetName }"/></td>
      				<td><c:out value="${ project.description }"/></td>
    			</tr>
   			</c:forEach>
 		</tbody>
	</table>
      			</div>
    			</div>
  			</div>
		</div>
	</div>
</body>
</html>