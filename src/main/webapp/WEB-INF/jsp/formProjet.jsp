<%-- 
    Document   : formProjet
    Created on : 20 oct. 2016, 15:37:52
    Author     : Vladimir
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>
<%-- <p:header title="${titre}"/> --%>

<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h4 class="modal-title">${titre}</h4>
</div>

<form:form method="post" modelAttribute="projet" class="form-horizontal">
    <div class="modal-body"> 
        <c:if test="${action != 'Modifier'}">
            <!--Createur:-->
            <%--<form:input  path="createur.idPersonne"/>--%>
            <%--<form:errors path="createur.idPersonne" cssClass="erreur"/>--%>
            <!--<br/>-->
            <div class="form-group">
                <label class="col-xs-3 control-label">Promotion :</label>
                <div class="col-xs-5">
                    <form:select class="form-control" path="promotion.id">  
                        <c:forEach items="${promotions}" var="promotion">
                            <form:option value="${promotion.getId()}">${promotion.getName()}</form:option>  
                        </c:forEach>
                    </form:select>  
                </div>
            </div>
        </c:if>
        <div class="form-group">
            <label class="col-xs-3 control-label">Sujet:</label>
            <div class="col-xs-5">
                <form:input path="sujet" class="form-control"/>
                <form:errors path="sujet" cssClass="erreur"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-3 control-label">Titre:</label>
            <div class="col-xs-5">
                <form:input class="form-control" path="titre"/>
                <form:errors path="titre" cssClass="erreur"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-xs-3 control-label">Date limite:</label>
            <div class="col-xs-5">
                <form:input class="form-control" path="dateLimite" placeholder="YYYY/MM/DD"/>Date format(YYY/MM/DD) 
                <form:errors path="dateLimite" cssClass="erreur"/>
            </div>
        </div>
        <div class="modal-footer">
            <button type="submit" class="btn btn-primary">${action}</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
        </div>
    </div> 
    <div>${message}</div>
</form:form>
<!--</div> -->

