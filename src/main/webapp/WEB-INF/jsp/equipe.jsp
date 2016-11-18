<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- 
    Document   : equipe
    Created on : 27 oct. 2016, 14:17:30
    Author     : Vladimir
--%>
<div class="col-sm-8">
    <div class="well">
        <br/>Resume : ${equipe.resume}
        <br/>Projet : ${equipe.projet.titre} 
        <br/>Date de creation : ${equipe.dateCreation}
        <br/>Createur : ${equipe.createur.nom} 
<div class="row">
    <div class="col-dm-8 col-md-offset-3">
        <div class="col-md-4" >
            <c:if test="${message ==null}">
                <c:if test="${equipe.membres.size() != 0}">
                    <h3>Membres : </h3>     <ol>
                        <c:forEach items="${equipe.membres}" var="membre">

                            <li>${membre.nom} <a href="#" data-toggle="modal" data-target="#dialog" data-url="equipe-${equipe.id}/${membre.idPersonne}-sup-membre"> <span class="glyphicon glyphicon-remove" style="color:#FF0000;"></span></a></li>

                        </c:forEach>
                    </c:if>
                    <c:if test="${equipe.membres.size() == 0}">
                        <h1 style="color: red">Pas de membres</h1>
                    </c:if>
                </c:if>
                <c:if test="${message != null}">
                    <h1>${message}</h1>
                </c:if>
                <a href="#" data-toggle="modal" data-target="#dialog" data-url="equipe-${equipe.id}-new-membre">Ajouter un membre</a>
        </div>
        <div class="col-md-5">
            <h4>les personnes qui n'ont pas d'equipe : </h4>   <ol>
                <c:forEach items="${membreB}" var="membre">

                    <li>${membre.nom}</li>
                    </c:forEach>
            </ol>
        </div>
    </div>
</div>
    </div>
</div>

<div id="dialog" class="modal"><div class="modal-dialog"><div class="modal-content"></div></div></div>

