<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%> --%>

<jsp:useBean id="today" class="java.util.Date"/> 
<div class="col-sm-8">
    <div class="well">

        <p:header title="Projet"/>
        <h1>Titre : ${projet.titre}</h1>
        <dl class="dl-horizontal span4 offset4 centered col-md-offset-3">
            <dt class="col-sm-pull-10">Promotion :</dt><dd class="text-left"> ${projet.promotion.name}</dd>
            <dt class="text-right">Nb projets de la promotion :</dt><dd class="text-left">${projet.promotion.projects.size()}</dd>
            <dt>Nb etudiants de la promotion :</dt><dd class="text-left">${projet.promotion.etudiants.size()}</dd>
            <dt class="text-right">Crée par : </dt><dd class="text-left">${projet.createur.idPersonne} ${projet.createur.nom} ${projet.createur.prenom}</dd>
            <dt class="text-right">Sujet :</dt><dd class="text-left">${projet.getSujet()}</dd>
            <dt>Fin le :</dt><dd class="text-left">${projet.getDateLimite()}</dd>   
        </dl>
        <c:if test="${sessionScope['user'].profil == true }">
            <c:if test="${sessionScope['user'].idPersonne == projet.createur.idPersonne}">
                <a href="#" data-toggle="modal" data-target="#dialog" data-url="projet-${idProjet}-modifier">Modifier le projet</a>
            </c:if>
        </c:if>
    </div>

</div>

<div class="col-sm-8 ">
    <c:if test="${sessionScope['user'].profil == false }">
        <c:if test="${sessionScope['user'].promotions.size() != 0}">
            <c:forEach items="${sessionScope['user'].promotions}" var="promotion">
                <c:if test="${promotion.finDate.time gt today.time && promotion.debutDate.time lt today.time && promotion.id == projet.promotion.id }">
                    <p>${promotion.name} </p>
                    <div class="text-right">
                        <a class="text-left" href="#" data-toggle="modal" data-target="#dialog" data-url="projet-${idProjet}-new-equipe">Ajouter une nouvelle equipe</a>
                    </div>
                </c:if>
            </c:forEach>
        </c:if>

    </c:if>
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
                                <c:if test="${sessionScope['user'].profil == false && sessionScope['user'].idPersonne == equipe.createur.idPersonne }">
                                    <a href="#" data-toggle="modal" data-target="#dialog" data-url="equipe-${equipe.id}-modifier"><span class="glyphicon glyphicon-pencil"></span>modifier</a>
                                </c:if>
                            </td>
                            <td>

                                <c:if test="${message ==null}">
                                    <c:if test="${equipe.membres.size() != 0}">

                                        <dl class="dl-horizontal">
                                            <c:forEach items="${equipe.membres}" var="membre">
                                                <dt><a href="personne-${membre.idPersonne}">${membre.nom}</a></dt> <dd> 
                                                    <c:if test="${sessionScope['user'].profil == false && sessionScope['user'].idPersonne == equipe.createur.idPersonne }">
                                                        <c:if test="${sessionScope['user'].idPersonne != membre.idPersonne }">
                                                            <a href="#" data-toggle="modal" data-target="#dialog" data-url="equipe-${equipe.id}/${membre.idPersonne}-sup-membre"> <span class="glyphicon glyphicon-remove" style="color:#FF0000;"></span></a>
                                                            </c:if>
                                                        </c:if>
                                                </dd>
                                            </c:forEach>
                                        </dl>
                                    </c:if>
                                    <c:if test="${sessionScope['user'].profil == false && sessionScope['user'].idPersonne == equipe.createur.idPersonne }">
                                        <a href="#" data-toggle="modal" data-target="#dialog" data-url="equipe-${equipe.id}-new-membre">Ajouter un membre</a>
                                    </c:if>
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

                <li>${membre.idPersonne} ${membre.nom}</li>
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
<c:forEach items="${projet.promotion.etudiants}" var="etudiant2">

    <br/>${etudiant2.idPersonne} ${etudiant2.nom}
</c:forEach>






<div id="dialog" class="modal"><div class="modal-dialog"><div class="modal-content"></div></div></div>