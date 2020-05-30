<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
</style>

</head>
<body>

	<h1>Page Utilisateur</h1>

	</br>
	
	<% String email = (String)session.getAttribute("utilisateurEmail");
	out.println("<ul>Email " + " = " + email + "</ul>");
	%>
	
	<% String mdp = (String)session.getAttribute("utilisateurMDP");
	out.println("<ul>Mot De Passe " + " = " + mdp + "</ul>");
	%>
	</br>

	<a class="btn btn-primary" href="deconnexion"> Deconnexion </a>

</body>
</html>