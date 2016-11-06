
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />
<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>
    <p:header title="${titre}"/>
        <h1>${titre}</h1>
       
    <ul>
        <li><a href="projet-1">projet-1</a></li>
        <li><a href="projet-1-new-equipe">projet-1-new-equipe</a></li>
        <li><a href="projet">projets</a></li>
        <li><a href="new-projet">new-projet</a></li>
        <li><a href="projet-4-modifier">projet-4-modifier</a></li>
        <li><a href="equipe-1">equipe-1</a></li>
        <li><a href="equipe-7-new-membre">equipe-7-new-membre</a></li>
        
    </ul>
    </body>
</html>


