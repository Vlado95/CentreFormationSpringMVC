<%-- 
    Document   : registrationForm
    Created on : 20 févr. 2017, 00:20:27
    Author     : Vladimir
--%>

<%-- 
    Document   : formProjet
    Created on : 20 oct. 2016, 15:37:52
    Author     : Vladimir
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>
<%-- <p:header title="${titre}"/> --%>
<%-- 
    Document   : connection
    Created on : 18 nov. 2016, 00:04:58
    Author     : Vladimir
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}"
       scope="request" />
<!DOCTYPE html>
<html>
    <head>
        <title>connexion</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet" />

<!-- Latest compiled and minified JavaScript -->

        <!--<link rel="stylesheet" href="../resources/css/style.css">-->
    </head>
    <body>

<!--<h2>$//{url}</h2>
<p>$//{url2}</p>-->
<form:form method="post" modelAttribute="user" class="form-horizontal"> 
            <!--Createur:-->
            <%--<form:input  path="createur.idPersonne"/>--%>
            <%--<form:errors path="createur.idPersonne" cssClass="erreur"/>--%>
            <!--<br/>-->
            <div class="form-group">
                <label class="col-xs-3 control-label">Nom :</label>
                <div class="col-xs-5">
                <form:input path="nom" class="form-control"/>
                <form:errors path="nom" cssClass="erreur"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-3 control-label">Prenom :</label>
                <div class="col-xs-5">
                <form:input path="prenom" class="form-control"/>
                <form:errors path="prenom" cssClass="erreur"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-3 control-label">E-Mail :</label>
                <div class="col-xs-5">
                <form:input path="email" class="form-control"/>
                <form:errors path="email" cssClass="erreur"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-3 control-label">Confiramtion E-Mail :</label>
                <div class="col-xs-5">
                <form:input path="confirmEmail" class="form-control"/>
                <form:errors path="confirmEmail" cssClass="erreur"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-3 control-label">Mot De Passe:</label>
                <div class="col-xs-5">
                <form:password path="password" class="form-control"/>
                <form:errors path="password" cssClass="erreur"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-3 control-label">Confirmation Mot de Passe :</label>
                <div class="col-xs-5">
                <form:password path="ConfirmPassword" class="form-control"/>
                <form:errors path="ConfirmPassword" cssClass="erreur"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-3 control-label">Adresse :</label>
                <div class="col-xs-5"> 
                <form:textarea path="rue" class="form-control"/>
                <form:errors path="rue" cssClass="erreur"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-3 control-label">Code postal :</label>
                <div class="col-xs-5">
                <form:input path="codePostal" class="form-control"/>
                <form:errors path="codePostal" cssClass="erreur"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-3 control-label">Ville :</label>
                <div class="col-xs-5">
                <form:input path="ville" class="form-control"/>
                <form:errors path="ville" cssClass="erreur"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-3 control-label">Pays :</label>
                <div class="col-xs-5">
                <form:input path="pays" class="form-control"/>
                <form:errors path="pays" cssClass="erreur"/>
                </div>
            </div>
                
            <div class="form-group">
                <label class="col-xs-3 control-label">Tél Mobile:</label>
                <div class="col-xs-5">
                <form:input path="mobile" class="form-control"/>
                <form:errors path="mobile" cssClass="erreur"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-3 control-label">Tél Fixe :</label>
                <div class="col-xs-5">
                <form:input path="fixe" class="form-control"/>
                <form:errors path="fixe" cssClass="erreur"/>
                </div>
            </div>
      
            <button type="submit" class="btn btn-primary">${action}</button>


    <div>${message}</div>
</form:form>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>

</body>
</html>
