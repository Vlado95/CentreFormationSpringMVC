<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>


<div class="col-sm-8">
    <div class="well">
        <dl class="dl-horizontal " style="margin-left: 120px;">
            <dt class="col-sm-pull-10">id :</dt><dd class="col-md-offset-4" style="margin-left: 120px;">mmmm</dd>
            <dt>NOM :</dt><dd style="margin-left: 120px;">mfmfmfm</dd>
            <dt>AGE :</dt><dd style="margin-left: 120px;">nsksbh</dd>
            <dt>SEXE :</dt><dd style="margin-left: 120px;">bqbqbqivbv</dd>
        </dl>

        <p:header title="Projet"/>
        <button class="btn btn-primary">Primary</button>
        <h1>Titre : ${projet.titre}</h1>
        <br/>Promotion : ${projet.promotion.name}
        <br/>Nb projets de la promotion : ${projet.promotion.projects.size()}
        <br/>Nb etudiants de la promotion : ${projet.promotion.etudiants.size()}
        <br/>Créé par : ${projet.createur.idPersonne} ${projet.createur.nom} ${projet.createur.prenom}
        <br/>Sujet : ${projet.getSujet()}
        <br/>Créer le : ${projet.getDateCreation()}
        <br/>Fin le : ${projet.getDateLimite()}
    </div>

</div>

<br/>Les equipes : 
<div class="col-sm-8 ">
    <a href="#" data-toggle="modal" data-target="#dialog" data-url="projet-${idProjet}-new-equipe">Créer Equipe</a>
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
                            <td>  ${equipe.resume}  </td>
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


    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Equipes</th>
                <th>Membres d'equipes</th>
                <th>Personnes sans eq</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>John</td>
                <td>Doe</td>
                <td>john@example.com</td>
            </tr>
            <tr>
                <td>Mary</td>
                <td>Moe</td>
                <td>mary@example.com</td>
            </tr>
            <tr>
                <td>July</td>
                <td>Dooley</td>
                <td>july@example.com</td>
            </tr>
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


<table class="table table-bordered">
    <thead>
        <tr>
            <th>Equipes</th>
            <th>Membres d'equipes</th>
            <th>Personnes sans eq</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>John</td>
            <td>Doe</td>
            <td>john@example.com</td>
        </tr>
        <tr>
            <td>Mary</td>
            <td>Moe</td>
            <td>mary@example.com</td>
        </tr>
        <tr>
            <td>July</td>
            <td>Dooley</td>
            <td>july@example.com</td>
        </tr>
    </tbody>
</table>







<div id="dialog" class="modal"><div class="modal-dialog"><div class="modal-content"></div></div></div>