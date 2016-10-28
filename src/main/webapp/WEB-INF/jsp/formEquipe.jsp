<%-- 
    Document   : formEquipe
    Created on : 13 oct. 2016, 15:29:08
    Author     : Vladimir
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>
<%-- <p:header title="${titre}"/> --%>
    <h1>${titre}</h1>
    <form:form method="post" modelAttribute="equipe">
      Createur: <form:input path="idCreateur"/>
      <form:errors path="idCreateur" cssClass="erreur"/>
      <br/>
      Resume : <form:input path="resume"/>
      <form:errors path="resume" cssClass="erreur"/>
      <br/>
      <button type="submit">${action}</button>
      <div>${message}</div>
    </form:form>
      

