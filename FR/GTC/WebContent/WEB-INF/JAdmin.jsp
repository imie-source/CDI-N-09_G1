<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="model.Personne"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr" lang="fr"
	dir="ltr">
<head>

 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<base href="/GTC/" />
<link rel=stylesheet type="text/css" href="css/style.css">
<title>Administration</title>
</head>
<body>

	<%@ include file="/WEB-INF/header.jsp"%>
	<%@include file="/WEB-INF/headerAdmin.jsp"%>
	<div id="divProfil">

		<c:choose>
			<c:when test="${userId == 3}">
				<br>
				<br>
				<br>
				<h4>mode super-admin : imports/exports de fichiers étudiants</h4>

				<%
					String impnok = (String) request
									.getAttribute("importImpossibleLoginDouble");
							if (impnok == "true") {
				%>
					<span style="color: #FF0000; text-align: center">
					<h2>il existe des doublons de login dans le fichier	sélectionné - Action annulée</h2>
				</span>
				<%
					}
				%>

				<span id="importerUnFichier">importer un fichier d'étudiants depuis le repertoire /home/imie/filrouge/data/</span>

				<form METHOD="POST" action="HImport">
					<input type="file" size="10" name="file1"> <br /> <input
						type="submit" value="Import" name="submitUpload">
				</form>

				<br>
				<br>
				<span id="importerUnFichier">exporter la liste des étudiants le repertoire /home/imie/filrouge/data/</span>
				<form METHOD="POST" action="HImport">
					<input type="file" size="10" name="file2"> <br /> <input
						type="submit" value="Export" name="submitDownload">
				</form>

			
			
			</c:when>
		</c:choose>
	</div>
</body>
</html>
