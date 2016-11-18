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

    <form:form method="post" modelAttribute="projet">
        <div class="modal-body"> 
        <c:if test="${action != 'Modifier'}">
            Createur: <form:input  path="createur.idPersonne"/>
            <form:errors path="createur.idPersonne" cssClass="erreur"/>
            <br/>
            Promotion : <form:select path="promotion.id">  
                <c:forEach items="${promotions}" var="promotion">
                    <form:option value="${promotion.getId()}">${promotion.getName()}</form:option>  
                </c:forEach>
            </form:select>  
            <br/>

        </c:if>
        Sujet : <form:input path="sujet"/>
        <form:errors path="sujet" cssClass="erreur"/>
        <br/>
        Titre : <form:input path="titre"/>
        <form:errors path="titre" cssClass="erreur"/>
        <br/>
        Date limite : <form:input path="dateLimite" placeholder="YYYY-MM-DD"/>
        <form:errors path="dateLimite" cssClass="erreur"/>
        <br/> 
        <div class="modal-footer">
            <button type="submit" class="btn btn-default">${action}</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
        </div>
</div> 
        <div>${message}</div>
    </form:form>
</div> 