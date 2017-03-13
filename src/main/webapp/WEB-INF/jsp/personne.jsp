<%-- 
    Document   : personne
    Created on : 21 nov. 2016, 00:07:00
    Author     : Vladimir
--%>

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
        <h2>${personne.prenom}  ${personne.nom}</h2>
         <dl class="dl-horizontal span4 offset4 centered col-md-offset-3">
            <dt class="col-sm-pull-10">Promotion :</dt><dd class="text-left">  ${personne.promo}</dd>
            <dt class="text-right">Profil : </dt><dd class="text-left">${personne.role}</dd>
            <dt class="text-right">email : </dt><dd class="text-left">${personne.email}</dd>
            <dt class="text-right">Adresse  :</dt><dd class="text-left">7 impasse du barreau</dd>
            <dt></dt><dd class="text-left">95280 Jouy le Moutier</dd>   
        </dl>
    </div>
</div>

