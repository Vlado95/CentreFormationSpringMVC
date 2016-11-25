<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- 
    Document   : equipe
    Created on : 27 oct. 2016, 14:17:30
    Author     : Vladimir
--%>
<jsp:useBean id="today" class="java.util.Date"/> 
<div class="col-sm-8">
    <div class="well">
        <br/>Resume : ${equipe.resume}
        <br/>Projet : ${equipe.projet.titre} 
        <br/>Date de creation : ${equipe.dateCreation}
        <br/>Createur :<a href="personne-${equipe.createur.idPersonne}"> ${equipe.createur.nom} </a>
                                <c:if test="${sessionScope['user'].profil == false && sessionScope['user'].idPersonne == equipe.createur.idPersonne }">
                            <c:if test="${sessionScope['user'].idPersonne != membre.idPersonne }">

                                </br><a href="#" data-toggle="modal" data-target="#dialog" data-url="equipe-${equipe.id}-modifier">Modifier Equipe</a>
                            </c:if>
                        </c:if>
        <div class="row">
            <div class="col-dm-8 col-md-offset-3">
                <div class="col-md-4" >
                    <c:if test="${message ==null}">
                        <c:if test="${equipe.membres.size() != 0}">
                            <h3>Membres : </h3>     <ol>
                                <c:forEach items="${equipe.membres}" var="membre">

                                    <li><a href="personne-${membre.idPersonne}">${membre.nom}</a> 
                                        <c:if test="${sessionScope['user'].profil == false && sessionScope['user'].idPersonne == equipe.createur.idPersonne }">

                                            <a href="#" data-toggle="modal" data-target="#dialog" data-url="equipe-${equipe.id}/${membre.idPersonne}-sup-membre"> <span class="glyphicon glyphicon-remove" style="color:#FF0000;"></span></a>
                                            </c:if>
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
                        <c:if test="${sessionScope['user'].profil == false && sessionScope['user'].idPersonne == equipe.createur.idPersonne }">
                            <c:if test="${sessionScope['user'].idPersonne != membre.idPersonne }">

                                <a href="#" data-toggle="modal" data-target="#dialog" data-url="equipe-${equipe.id}-new-membre">Ajouter un membre</a>
                            </c:if>
                        </c:if>
                </div>
                <div class="col-md-5">
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

<div class="col-sm-8 ">
        <c:if test="${equipe.membres.size() != 0 }">   
            <c:forEach items="${equipe.membres}" var="membre">
                <c:if test="${membre.idPersonne == sessionScope['user'].idPersonne}"> 
                    <c:if test="${equipe.projet.dateLimite.time gt today.time && sessionScope['user'].profil == false }">
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
                                                ${file.dateMisaJr}
                                            </td>
                                            <td>
                                                <a href="download-document-${file.id}"><span class="glyphicon glyphicon-download-alt"></span></a>
                                                <a href="delete-document-${file.id}"><span class="glyphicon glyphicon-remove" style="color:#FF0000;"></span></a>
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
        <c:if test="${sessionScope['user'].idPersonne == equipe.projet.createur.idPersonne}"> 
              <c:if test="${files.size() != 0}">

                        <table class="table table-bordered table-curved well">
                            <thead>
                                <tr class="label-default" style="color: white;">
                                    <th>Nom du fichier</th>
                                    <th>Auteur</th>
                                    <th>Decription</th>
                                    <th>Type de fichier</th>
                                    <th>Date de Creation</th>
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

