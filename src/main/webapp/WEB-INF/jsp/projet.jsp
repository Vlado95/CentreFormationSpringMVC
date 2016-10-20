<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Projet</title>
    </head>
    <body>
        <h1>Titre : ${projet.titre}</h1>
        <br/>Promotion : {projet.promotion.name}
        <br/>Nb projets de la promotion : {projet.promotion.projects.size()}
        <br/>Nb etudiants de la promotion : {projet.promotion.etudiants.size()}
        <br/>Créé par : {projet.createur.nom}
        <br/>Sujet : ${projet.getSujet()}
        <br/>Créer le : ${projet.getDateCreation()}
        <br/>Fin le : ${projet.getDateLimite()}
        
    </body>
</html>
