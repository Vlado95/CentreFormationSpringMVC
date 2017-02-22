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
                <form:input path="password" class="form-control"/>
                <form:errors path="password" cssClass="erreur"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-3 control-label">Confirmation Mot de Passe :</label>
                <div class="col-xs-5">
                <form:input path="ConfirmPassword" class="form-control"/>
                <form:errors path="ConfirmPassword" cssClass="erreur"/>
                </div>
            </div>
      
            <button type="submit" class="btn btn-primary">${action}</button>


    <div>${message}</div>
</form:form>
<!--</div> -->


