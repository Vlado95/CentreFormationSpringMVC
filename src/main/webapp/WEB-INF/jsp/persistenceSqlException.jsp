<%-- 
    Document   : persistenceSqlException
    Created on : 17 nov. 2016, 12:32:58
    Author     : Vladimir
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

    <p:header title="${titre}"/>
    <c:set var="sujet" value="Probl�me%20BD%20�%20${date}"/>
    <h1>Un probl�me est survenu avec la base de donn�es Hibernate.</h1>
    <p><a href="mailto:admin-appli@cefisi.com?subject=${sujet}">Signaler ce probl�me</a></p>
    <pre>${exception.getMessage()}</pre>
  </body>
</html>
