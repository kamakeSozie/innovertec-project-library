<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="messages" />
<fmt:message key="message.password" var="noPass" />
<fmt:message key="message.username" var="noUser" />


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<style>
.bodys {
  background-image: 3d.svg;
}
</style>
</head>
<body class="bodys">
<div class="card col-md-10 ml-auto mr-auto mt-3 bg-info text-white">
	<h4 class="text-center">Bienvenue dans votre logiciel de gestion des employées !</h4>
</div>
<div class="card col-md-4 ml-auto mr-auto mt-5 pt-5 alert alert-secondary">

	 <form action = "#" method = "post">
    <fieldset>
        <legend class="text-center"> Veuillez vous connecter </legend>
        
        <div class="form-row">
    		<div class="form-group col-md-12">
      			<label for="name">Non d'utilisateur</label>
      				<input type="text" class="form-control" name="username" id="username" placeholder="Entrer votre nom" required="required">
    		</div>
    		<div class="form-group col-md-12">
      			<label for="name">Mot de passe</label>
      				<input type="password" class="form-control" name="password" id="password" placeholder="Entrer votre mot de passe" required="required">
    		</div>
            	<button type ="submit" class="btn btn-primary col-md-6 ml-auto mr-auto" id="send">
            		Connectez-vous
            	</button>
    	</div>
    </fieldset>
    <a href="https://mail.google.com/mail/u/0/#inbox">archive@innovertec.com</a>
 </form>
</div>
</body>

</html>