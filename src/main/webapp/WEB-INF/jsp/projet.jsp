<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<p:header title="Projet"/>
        <button class="btn btn-primary">Primary</button>
        <h1>Titre : ${projet.titre}</h1>
        <br/>Promotion : ${projet.promotion.name}
        <br/>Nb projets de la promotion : ${projet.promotion.projects.size()}
        <br/>Nb etudiants de la promotion : ${projet.promotion.etudiants.size()}
        <br/>Créé par : ${projet.createur.nom} ${projet.createur.prenom}
        <br/>Sujet : ${projet.getSujet()}
        <br/>Créer le : ${projet.getDateCreation()}
        <br/>Fin le : ${projet.getDateLimite()}
        <br/>Les equipes : 
        <c:if test="${message ==null}">
            <c:if test="${equipes.size() != 0}">
                <ol>
                    <c:forEach items="${equipes}" var="equipe">
                        <li>
                            ${equipe.getResume()}
                            <c:if test="${message ==null}">
                                <c:if test="${equipe.membres.size() != 0}">
                                    <br/>Membres :      <ol>
                                        <c:forEach items="${equipe.membres}" var="membre">
                                            <li>${membre.nom}</li>
                                            </c:forEach>
                                    </ol>
                                </c:if>
                                <c:if test="${equipe.membres.size() == 0}">
                                    <h1 style="color: red">Pas de membres</h1>
                                </c:if>
                            </c:if>
                            <c:if test="${message != null}">
                                <h1>${message}</h1>
                            </c:if>

                        </li>
                    </c:forEach>
                </ol>
            </c:if>
            <c:if test="${equipes.size() ==0}">
                <h1 style="color: red">Pas d'equipes</h1>
            </c:if>
        </c:if>
        <c:if test="${message != null}">
            <h1>${message}</h1>
        </c:if>
        Etudiants de la promotion :
        <c:forEach items="${projet.promotion.etudiants}" var="etudiant2">

            <br/>${etudiant2.idPersonne} ${etudiant2.nom}
        </c:forEach>

        <script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
        <script type="text/javascript" src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </body>
</html>