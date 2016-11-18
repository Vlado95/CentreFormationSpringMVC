<%-- 
    Document   : formEquipe
    Created on : 13 oct. 2016, 15:29:08
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

<form:form method="post" modelAttribute="equipe">
    <c:if test="${action == 'Créer'}">
        <div class="modal-body">            
            <!--            Createur: <form :input path="createur.idPersonne"/>
                        <form :errors path="createur.idPersonne" cssClass="erreur"/>
                        <br/>-->
            Resume : <form:input path="resume"/>
            <form:errors path="resume" cssClass="erreur"/>
            <br/>
            <div class="modal-footer">
                <button type="submit" class="btn btn-default">${action}</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
            </div> 
        </div>                     
    </c:if>
    
    <c:if test="${action == 'Modifier'}">
        <div class="modal-body">            
            projet :${equipe.projet.titre} <br/>
            promotion :${equipe.projet.promotion.name} <br/>
            Resume : <form:input path="resume"/>
            <form:errors path="resume" cssClass="erreur"/>
            <br/>
            <div class="modal-footer">
                <button type="submit" class="btn btn-default">${action}</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
            </div> 
        </div>                     
    </c:if>





    <c:if test="${action == 'Ajouter'}">
        <div class="modal-body">  
            projet :${equipe.projet.titre} <br/>
            promotion :${equipe.projet.promotion.name} <br/>
            equipe :${equipe.resume}<br/>
            <c:if test="${membreB.size() !=0}">
                nouveau membre : <form:select path="createur.idPersonne"> 

                    <c:forEach items="${membreB}" var="membre"> 

                        <form:option value="${membre.getIdPersonne()}">${membre.getNom()}</form:option>  

                    </c:forEach> 


                </form:select> 
                <div class="modal-footer">
                    <button type="submit" class="btn btn-default">${action}</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
                </div> 
            </c:if>
            <c:if test="${membreB.size() == 0}">
                <p style="color: green">Pas de personne à ajouter</p> 
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
                </div> 
            </c:if>
            <br/>

        </div>   
    </c:if>

    <c:if test="${action == 'Suprimer'}">
        <div class="modal-body">  
            Voulez vous supprimer ${membre.nom} dans l'equipe ${equipe.resume} ?<br/>
            <div class="modal-footer">
                <button type="submit" class="btn btn-default">${action}</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
            </div> 
            <br/>

        </div>   
    </c:if>


    <div>${message}</div>
</form:form>







