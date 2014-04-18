<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="java.util.Date"%>
<%@page import="model.Personne"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr" lang="fr"
	dir="ltr">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<base href="/GTC/" />
<link rel=stylesheet type="text/css" href="css/style.css">
<link href="css/south-street/jquery-ui-1.10.4.custom.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="js/jquery-ui-1.10.4.custom.js"></script>

<title>Administration</title>
<SCRIPT type="text/javascript">
	
	$(document).ready(function() {
	
		$('#divProfil').hide();
		if ("${loguedPerson.role.roleId}" != 1) {
			$('#divProfil').show();
		}

		$('.onlyadmin').hide();
		console.log('1;');
		if ("${loguedPerson.role.roleId}" != 1) {
			$('.onlyadmin').show();
			console.log('2;');
		}
		
		$('#afficheListeUsers').on('click', function(e) {
			document.location.href="/GTC/HPersonne";
		});
		$('#afficheListePromotions').on('click', function(e) {
			document.location.href="/GTC/HPromotion";
 		});
		$('#afficheListeProjets').on('click', function(e) {
			document.location.href="/GTC/HProjet";
 		});
		$('#importerUnFichier').on('click', function(e) {
			document.location.href="/GTC/HImport";
 		});

	});
</SCRIPT>
</head>
<body>



	<%@ include file="/WEB-INF/header.jsp"%>
	<div id="divProfil">

		<span> page Admin </span><br>
		<BUTTON id="afficheListeUsers">Gestion des
			utilisateurs</BUTTON>
		
		<br><br>
		<BUTTON id="afficheListePromotions">Gestion des
			promotions</BUTTON>
		<br><br>
		<BUTTON id="afficheListeProjets">Gestion des
			projets</BUTTON>
			
			<br><br>
		<BUTTON id="afficherListeCompetences">Gestion des compétences</BUTTON>
	

		<br><br>
		<BUTTON id="importerUnFichier">importer un fichier d'étudiants</BUTTON>
</div>
</body>
</html>
