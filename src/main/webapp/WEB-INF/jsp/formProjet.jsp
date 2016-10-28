<%-- 
    Document   : formProjet
    Created on : 20 oct. 2016, 15:37:52
    Author     : Vladimir
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>
<%-- <p:header title="${titre}"/> --%>
    <h1>${titre}</h1>
    <form:form method="post" modelAttribute="projet">
      Createur: <form:input path="createur.idPersonne"/>
      <form:errors path="createur.idPersonne" cssClass="erreur"/>
      <br/>
      Sujet : <form:input path="sujet"/>
      <form:errors path="sujet" cssClass="erreur"/>
      <br/>
      Titre : <form:input path="titre"/>
      <form:errors path="titre" cssClass="erreur"/>
      <br/>
      
     Promotion : <form:select path="promotion.id">  
          <c:forEach items="${promotions}" var="promotion">
              <form:option value="${promotion.getId()}">${promotion.getName()}</form:option>  
          </c:forEach>
      </form:select>  
      <br/>
      Date limite : <form:input path="dateLimite" placeholder="YYYY-MM-DD"/>
      <form:errors path="dateLimite" cssClass="erreur"/>
      <br/> 
      <button type="submit">${action}</button>
      <div>${message}</div>
    </form:form>