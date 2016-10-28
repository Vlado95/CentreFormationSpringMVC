<%@tag description="En-tête des pages" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}"
       scope="request" />

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="title" required="true" description="titre de la page" %>
<!DOCTYPE html>
<html>
  <head>
    <title>${title}</title>
    <link rel="stylesheet" type="text/css" href="${cp}/static/css/site.css"/>
    <meta name="viewport"
          content="width=device-width, initial-scale=1">
  </head>
  <body>
    <nav id="menu">
      <a href=".">Accueil</a> -
      <a href="projet">Projets</a> -
      <a href="session">Manipuler les sessions</a>
      <form style="float: right" action="connexion" method="post">
        <button type="submit">Déconnecter ${sessionScope['user'].login}</button>
        <input type="hidden" name="action" value="deconnecter"/>
      </form>
    </nav>
    <hr/>