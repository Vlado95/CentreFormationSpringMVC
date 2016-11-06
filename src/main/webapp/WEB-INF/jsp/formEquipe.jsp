<%-- 
    Document   : formEquipe
    Created on : 13 oct. 2016, 15:29:08
    Author     : Vladimir
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>
<%-- <p:header title="${titre}"/> --%>
    <h1>${titre}</h1>
    <form:form method="post" modelAttribute="equipe">
        <c:if test="${action != 'Ajouter'}">
      Createur: <form:input path="createur.idPersonne"/>
      <form:errors path="createur.idPersonne" cssClass="erreur"/>
      <br/>
      Resume : <form:input path="resume"/>
      <form:errors path="resume" cssClass="erreur"/>
      <br/>
      <button type="submit">${action}</button>
        </c:if>
      
      <c:if test="${action == 'Ajouter'}">
          projet :${equipe.projet.titre} <br/>
          promotion :${equipe.projet.promotion.name} <br/>
          equipe :${equipe.resume}<br/>
          <c:if test="${membreB.size() !=0}">
      nouveau membre : <form:select path="createur.idPersonne"> 
          
              <c:forEach items="${membreB}" var="membre"> 
                  
                <form:option value="${membre.getIdPersonne()}">${membre.getNom()}</form:option>  
                
                    </c:forEach> 
          
                    
        </form:select> 
      <button type="submit">${action}</button>
          </c:if>
        <c:if test="${membreB.size() == 0}">
                        <p style="color: green">Pas de personne à ajouter</p> 
                    </c:if>
        <br/>
      </c:if>
      
      <div>${message}</div>
    </form:form>
      

