<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- 
    Document   : equipe
    Created on : 27 oct. 2016, 14:17:30
    Author     : Vladimir
--%>
<jsp:useBean id="today" class="java.util.Date"/> 
<div class="col-sm-8 col-dm-8 col-lg-8 ">
    <div class="well">
       <!--${auteur2.name}-->
        <br/>Resume : ${equipe.resume}
        <br/>Projet : ${equipe.projet.titre} 
        <br/>Date de creation : ${equipe.dateCreation}
        <br/>Createur :<a href="personne-${equipe.createur.idPersonne}"> ${equipe.createur.nom} </a>
        <sec:authorize access="hasRole('Etudiant')">
            <c:if test="${pageContext.request.userPrincipal.name.equals(equipe.createur.email)}">
                <%--<c:if test="${sessionScope['user'].idPersonne != membre.idPersonne }">--%>
                </br><a href="#" data-toggle="modal" data-target="#dialog" data-url="equipe-${equipe.id}-modifier">Modifier Equipe</a>
                <%--</c:if>--%>
            </c:if>
        </sec:authorize>

        <div class="row">
            <div class="col-dm-8 col-md-offset-3 col-sm-8 col-sm-offset-3 col-lg-8 col-lg-offset-3">
                <div class="col-sm-4 col-md-4 col-lg-4" >
                    <c:if test="${message ==null}">
                        <c:if test="${equipe.membres.size() != 0}">
                            <h3>Membres : </h3>     <ol>
                                <c:forEach items="${equipe.membres}" var="membre">

                                    <li><a href="personne-${membre.idPersonne}">${membre.nom}</a> 
                                        <sec:authorize access="hasRole('Etudiant')">
                                            <c:if test="${pageContext.request.userPrincipal.name.equals(equipe.createur.email)}"> 
                                                <a href="#" data-toggle="modal" data-target="#dialog" data-url="equipe-${equipe.id}/${membre.idPersonne}-sup-membre"> <span class="glyphicon glyphicon-remove" style="color:#FF0000;"></span></a>
                                                </c:if>
                                            </sec:authorize>
                                    </li>

                                </c:forEach>
                            </c:if>
                            <c:if test="${equipe.membres.size() == 0}">
                                <h1 style="color: red">Pas de membres</h1>
                            </c:if>
                        </c:if>
                        <c:if test="${message != null}">
                            <h1>${message}</h1>
                        </c:if>
                        <sec:authorize access="hasRole('Etudiant')">
                            <c:if test="${pageContext.request.userPrincipal.name.equals(equipe.createur.email)}">
                                <a href="#" data-toggle="modal" data-target="#dialog" data-url="equipe-${equipe.id}-new-membre">Ajouter un membre</a>
                            </c:if>
                        </sec:authorize>
                </div>
                <div class="col-md-5 col-sm-5 col-lg-5">
                    <h4>les personnes qui n'ont pas d'equipe : </h4>   <ol>
                        <c:forEach items="${membreB}" var="membre">

                            <li><a href="personne-${membre.idPersonne}">${membre.nom}</a></li>
                            </c:forEach>
                    </ol>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="col-sm-9  col-md-9 col-lg-9">
    <c:if test="${equipe.membres.size() != 0 }">   
        <c:forEach items="${equipe.membres}" var="membre">
            <c:if test="${pageContext.request.userPrincipal.name.equals(membre.email)}"> 
                <c:if test="${equipe.projet.dateLimite.time gt today.time}">
                    <div class="text-right">
                        <a class="text-left" href="#" data-toggle="modal" data-target="#dialog" data-url="upload-${equipe.id}">Ajouter un fichier</a>
                    </div>
                </c:if>

                <c:if test="${files.size() != 0}">

                    <table class="table table-bordered table-curved well">
                        <thead>
                            <tr class="label-default" style="color: white;">
                                <th>Nom du fichier</th>
                                <th>Auteur</th>
                                <th>Decription</th>
                                <th>Type de fichier</th>
                                <th>Date de Creation</th>
                                <th>mise à jour par</th>
                                <th>Date de mise à jour</th>
                                <th>Action</th>

                            </tr>
                        </thead>



                        <tbody>

                            <c:if test="${message ==null}">
                                <%--<c:if test="${equipes.size() != 0}">--%>

                                <c:forEach items="${files}" var="file">
                                    <tr>    
                                        <td> 
                                            ${file.fileName}
                                        </td>
                                        <td>   
                                            ${file.auteur.nom}
                                        </td>
                                        <td> 
                                            ${file.description}
                                        </td>
                                        <td>
                                            ${file.type}
                                        </td>

                                        <td> 
                                            ${file.dateAjout}
                                        </td>
                                        <td>   
                                            ${file.persoAjour.nom}
                                        </td>
                                        <td>
                                            ${file.dateMisaJr}
                                        </td>
                                        <td>
                                            <a href="download-document-${file.id}"><span class="glyphicon glyphicon-download-alt"></span></a>
                                            <a href="delete-document-${file.id}"><span class="glyphicon glyphicon-remove" style="color:#FF0000;"></span></a>
                                            <a href="#" data-toggle="modal" data-target="#dialog" data-url="upload-${file.equipe.id}/${file.id}-modify">
                                                <span class="glyphicon glyphicon-pencil"></span>
                                            </a>
                                        </td>

                                    </tr>
                                </c:forEach>

                                <%--</c:if>--%>

                            </c:if>
                            <c:if test="${message != null}">
                            <h1>${message}</h1>
                        </c:if>

                        </tbody>
                    </table>   
                </c:if> 
                <c:if test="${files.size() ==0}">
                    <p style="color: red">PAS DES FICHIERS</p>
                </c:if>
            </c:if>
        </c:forEach>
    </c:if>
    <c:if test="${pageContext.request.userPrincipal.name.equals(equipe.projet.createur.email)}"> 
        <c:if test="${files.size() != 0}">

            <table class="table table-bordered table-curved well">
                <thead>
                    <tr class="label-default" style="color: white;">
                        <th>Nom du fichier</th>
                        <th>Auteur</th>
                        <th>Decription</th>
                        <th>Type de fichier</th>
                        <th>Date de Creation</th>
                        <th>mise à jour par</th>
                        <th>Date de mise à jour</th>


                    </tr>
                </thead>



                <tbody>

                    <c:if test="${message ==null}">
                        <%--<c:if test="${equipes.size() != 0}">--%>

                        <c:forEach items="${files}" var="file">
                            <tr>    
                                <td> 
                                    ${file.fileName}
                                </td>
                                <td>
                                    ${file.idAuteur}
                                </td>
                                <td> 
                                    ${file.description}
                                </td>
                                <td>
                                    ${file.type}
                                </td>
                                <td> 
                                    ${file.dateAjout}
                                </td>
                                <td>
                                    <c:if test="${auteurAjour.size() != 0}">
                                        <c:forEach items="${auteurAjour}" var="auteurAjour">
                                            <c:if test="${file.idAuteur == auteurAjour.idPersonne}">    
                                                ${auteurAjour.nom}
                                            </c:if>
                                            <c:if test="${auteurAjour.size() == 0}">
                                                pas de mise à jour 
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </td>
                                <td>
                                    ${file.dateMisaJr}
                                </td>
                            </tr>
                        </c:forEach>

                        <%--</c:if>--%>

                    </c:if>
                    <c:if test="${message != null}">
                    <h1>${message}</h1>
                </c:if>

                </tbody>
            </table>   
        </c:if> 
        <c:if test="${files.size() ==0}">
            <p style="color: red">PAS DES FICHIERS</p>
        </c:if>

    </c:if>

</div> 
<div id="dialog" class="modal"><div class="modal-dialog"><div class="modal-content"></div></div></div>

