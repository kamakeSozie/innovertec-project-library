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
	$('#showHide').hide();
	$('#bienvenue').show();
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
			
			    $('#showHide').show();
				$('#bienvenue').hide();
		});
	})
	
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

				<select id="SelectedEmpId" class="form-control col-md-4 ml-auto mr-auto mt-2 mb-2">
					<option value="NONE">--Selectionner projet--</option>
				<c:forEach items="${ listProject }" var="projects">
					<option value="${projects.projetName}&${projects.projetId}">${projects.projetName}</option>
				</c:forEach>
				</select>
<div id="resultGetAllCustomerDiv"></div>
<div id="showHide">
	<div class="card col-md-10 ml-auto mr-auto mt-3 mb-5">
	<table class="table table-striped" id="tableId">
  		<thead>
    		<tr>
      			<th scope="col">#</th>
      			<th scope="col">Nom</th>
      			<th scope="col">Email</th>
      			<th scope="col">Projet</th>
    		</tr>
  		</thead>
  		<tbody>
  			<c:forEach items="${ listEmployeByProjetc }" var="employe">
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
<div class="col-md-6 mr-auto ml-auto mt-5 pt-5" id="bienvenue">
<div class="card bg-info text-white text-center">
	<h3><b><u>Bienvenue : ${ username }</u></b></h3></br>
	<h5>Veuillez sélectionner un projet pour plus de détails. Merci !</h5>
</div>
</div>
</body>
</html>