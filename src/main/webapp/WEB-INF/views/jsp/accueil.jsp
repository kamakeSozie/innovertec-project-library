<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Result page</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
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
        <a class="nav-link" href="#">Accueil <span class="sr-only">(current)</span></a>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0">
      <a class="nav-link" href="/login"><b>Se Connecter </b><span class="sr-only"></span></a>
    </form>
  </div>
</nav>
	
	
	<div class="container-fluid">
	<h3 class="text-center text-uppercase text-info"><u>Archive des projets terminés</u></h3>
	<div class="card-deck mt-5">
		<c:forEach items="${ allProjetActive }" var="allActive">
		
		<div class="col-md-6 mb-3">
			<div class="card border-primary">
  				<div class="card-header bg-info text-center text-white text-uppercase"><b><c:out value="${ allActive.projetName }"/></b></div>
  				<div class="card-body text-primary">
    				<p class="card-text">
    					<c:out value="${ allActive.description }"/>
    				</p>
  				</div>
			</div>
		</div>
			
   		</c:forEach>
	</div>
	</div>
</body>
</html>