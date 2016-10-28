<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- 
    Document   : equipe
    Created on : 27 oct. 2016, 14:17:30
    Author     : Vladimir
--%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Equipe</title>
    </head>
    <body>
        <br/>Resume : ${equipe.resume}
        <br/>Date de creation : ${equipe.dateCreation}
        <br/>Createur : ${equipe.createur.nom} 
         
        <a class="btn btn-primary btn-lg" href="#" role="button">Learn more</a>
        <c:if test="${message ==null}">
            <c:if test="${equipe.membres.size() != 0}">
           <br/>Membres :      <ol>
               <li>${equipe.createur.nom}</li>
             <c:forEach items="${equipe.membres}" var="membre">
                      
                    <li>${membre.nom}</li>
                </c:forEach>
                </ol>
            </c:if>
            <c:if test="${equipe.membres.size() == 0}">
                <h1 style="color: red">Pas de membres</h1>
            </c:if>
        </c:if>
        <c:if test="${message != null}">
            <h1>${message}</h1>
        </c:if>
    </body>
</html>
 