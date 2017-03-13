<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%> --%>

<jsp:useBean id="today" class="java.util.Date"/> 
<div class="col-sm-8 col-md-8" col-lg-8>
    <div class="well">

        <p:header title="Projet"/>
        <h1>Titre : ${projet.titre}</h1>
        <dl class="dl-horizontal span4 offset4 centered col-md-offset-3">
            <dt class="col-sm-pull-10">Promotion :</dt><dd class="text-left"> ${projet.promotion.name}</dd>
            <dt class="text-right">Crée par : </dt><dd class="text-left">${projet.createur.nom} ${projet.createur.prenom}</dd>
            <dt class="text-right">Sujet :</dt><dd class="text-left">${projet.getSujet()}</dd>
            <dt>Fin le :</dt><dd class="text-left">${projet.getDateLimite()}</dd>   
        </dl>
        <sec:authorize access="hasRole('Professeur')">
            <c:if test="${pageContext.request.userPrincipal.name.equals(projet.createur.email)}">
                <a href="#" data-toggle="modal" data-target="#dialog" data-url="projet-${idProjet}-modifier">Modifier le projet</a>
            </c:if>
        </sec:authorize>
    </div>

</div>

<div class="col-sm-8 col-md-8 col-lg-8 ">
    <sec:authorize access="hasRole('Etudiant')">
        <c:if test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.promotion.equals(projet.promotion.name)}">
            <div class="text-right">
                <a class="text-left" href="#" data-toggle="modal" data-target="#dialog" data-url="projet-${idProjet}-new-equipe">Ajouter une nouvelle equipe</a>
            </div>
        </c:if>
    </sec:authorize>
    <table class="table table-bordered table-curved well">
        <thead>
            <tr class="label-default" style="color: white;">
                <th>Equipes</th>
                <th>Membres</th>
            </tr>
        </thead>



        <tbody>

            <c:if test="${message ==null}">
                <c:if test="${equipes.size() != 0}">
                <ol>
                    <c:forEach items="${equipes}" var="equipe">
                        <tr>    
                            <td> <a href="equipe-${equipe.id}">${equipe.resume}</a> 
                                <sec:authorize access="hasRole('Etudiant')">
                                    <c:if test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.idPersonne == equipe.createur.idPersonne }">
                                        <a href="#" data-toggle="modal" data-target="#dialog" data-url="equipe-${equipe.id}-modifier"><span class="glyphicon glyphicon-pencil"></span>modifier</a>
                                    </c:if>
                                </sec:authorize>
                            </td>
                            <td>

                                <c:if test="${message ==null}">
                                    <c:if test="${equipe.membres.size() != 0}">

                                        <dl class="dl-horizontal">
                                            <c:forEach items="${equipe.membres}" var="membre">
                                                <dt><a href="personne-${membre.idPersonne}">${membre.nom}</a></dt> <dd> 
                                                    <sec:authorize access="hasRole('Etudiant')">
                                                        <c:if test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.idPersonne != membre.idPersonne && sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.idPersonne == equipe.createur.idPersonne }">
                                                            <a href="#" data-toggle="modal" data-target="#dialog" data-url="equipe-${equipe.id}/${membre.idPersonne}-sup-membre"> <span class="glyphicon glyphicon-remove" style="color:#FF0000;"></span></a>
                                                            </c:if>
                                                        </sec:authorize>
                                                </dd>
                                            </c:forEach>
                                        </dl>
                                    </c:if>
                                    <sec:authorize access="hasRole('Etudiant')">
                                        <c:if test="${ sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.idPersonne == equipe.createur.idPersonne }">
                                            <a href="#" data-toggle="modal" data-target="#dialog" data-url="equipe-${equipe.id}-new-membre">Ajouter un membre</a>
                                        </c:if>
                                    </sec:authorize>
                                    <c:if test="${equipe.membres.size() == 0}">
                                        <p style="color: red">Pas de membres</p>
                                    </c:if>
                                </c:if>
                                <c:if test="${message != null}">
                                    <h1>${message}</h1>
                                </c:if> 

                            </td>

                        </tr>
                    </c:forEach>
                </ol>
            </c:if>
            <c:if test="${projet.equipes.size() ==0}">
                <p style="color: red">Pas d'equipes</p>
            </c:if>
        </c:if>
        <c:if test="${message != null}">
            <h1>${message}</h1>
        </c:if>

        </tbody>
    </table>   
</div>  
<br/>Membres qui n'ont pas equipe :  
<c:if test="${message ==null}">
    <c:if test="${membreB.size() != 0}">
        <ol>
            <c:forEach items="${membreB}" var="membre">

                <li>${membre.nom}</li>
                </c:forEach>
        </ol>
    </c:if>
    <c:if test="${membreB.size() ==0}">
        <p style="color: green">Tous les membres ont equipe</p>
    </c:if>
</c:if>
<c:if test="${message != null}">
    <h1>${message}</h1>
</c:if> 

Etudiants de la promotion :
<c:if test="${projet.promotion.etudiants.size() != 0}">
 
        <c:forEach items="${projet.promotion.etudiants}" var="membreProm">
            <br/>${membreProm.nom}
            </c:forEach>

</c:if>


<div id="dialog" class="modal"><div class="modal-dialog"><div class="modal-content"></div></div></div>