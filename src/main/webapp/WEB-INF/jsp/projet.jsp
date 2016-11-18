<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%> --%>


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
        
         <a href="#" data-toggle="modal" data-target="#dialog" data-url="projet-${idProjet}-modifier">Modifier le projet</a>
    </div>

</div>
 
<div class="col-sm-8 ">
 <div class="text-right"><a class="text-left" href="#" data-toggle="modal" data-target="#dialog" data-url="projet-${idProjet}-new-equipe">Ajouter une nouvelle equipe</a>
 </div>
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
                            <td>  ${equipe.resume} <a href="#">Modifier l'equipe</a> </td>
                            <td>

                                <c:if test="${message ==null}">
                                    <c:if test="${equipe.membres.size() != 0}">
                                        <ol>
                                            <c:forEach items="${equipe.membres}" var="membre">

                                                <li>${membre.nom}</li>
                                                </c:forEach>
                                        </ol>
                                    </c:if>
                                    <a href="#" data-toggle="modal" data-target="#dialog" data-url="equipe-${equipe.id}-new-membre">Ajouter un membre</a>

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