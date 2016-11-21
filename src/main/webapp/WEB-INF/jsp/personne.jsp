<%-- 
    Document   : personne
    Created on : 21 nov. 2016, 00:07:00
    Author     : Vladimir
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- 
    Document   : equipe
    Created on : 27 oct. 2016, 14:17:30
    Author     : Vladimir
--%>

<jsp:useBean id="today" class="java.util.Date"/>  
<div class="col-sm-8">
    <div class="well">
        <h2>${personne.prenom}  ${personne.nom}</h2>
        <br/>ID : ${personne.idPersonne} 
        
        <c:if test="${personne.promotions.size() != 0}">
            <br/>Promotion : 
            <c:forEach items="${personne.promotions}" var="promotion">
                <c:if test="${promotion.finDate.time gt today.time && promotion.debutDate.time lt today.time}">
                    ${promotion.name} 
                </c:if>
            </c:forEach>
        </c:if>
        </br>Profil : 
        <c:if test="${personne.profil == false }">
            Etudiant    
        </c:if>
        <c:if test="${personne.profil == true }">
            Professeur    
        </c:if>
    </div>
</div>

