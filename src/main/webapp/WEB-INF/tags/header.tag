<%@tag description="En-tête des pages" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}"
       scope="request" />

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="title" required="true" description="titre de la page" %>
<!DOCTYPE html>

<!-- <ul class="nav navbar-nav">
        <li><a href=".">Accueil</a></li>
       <li><a href="projet">Projets</a></li>
       <li><a href="session">Manipuler les sessions</a></li>
       <div class="gauche">

      
      </div>
    </ul>-->

      <ul class="nav navbar-nav">
            
        <li class="active"><a href=".">Acceuil</a></li>
        <li><a href="projet">Projets</a></li>
        <li><a href="session">Manipuler les sessions</a></li>
      </ul>
      <ul class="navbar-form nav navbar-nav navbar-right">
        <li>        <c:if test="${sessionScope['user'] == null}">
          <form style="float: right" action="connexion" method="post">
            Login :
            <input type="text" name="login" value="${login}"/>
            Mot de passe :
            <input type="password" name="password" size="12"/>
            <button type="submit">Connecter</button>
            <br/>${msgConnexion}
          </form>
        </c:if>
        <c:if test="${sessionScope['user'] != null}">
          <form action="deconnexion" method="post">
            <button type="submit"><span class="glyphicon glyphicon-log-in"></span>Déconnecter ${sessionScope['user'].nom}</button>
          </form>
        </c:if>
           
      </ul>


<!--        <div class="nav navbar-nav navbar-right">
            

      
            
        </div>
    
    

   <nav id="menu">
      <div class="gauche">
      <a href=".">Accueil</a> -
      <a href="projet">Projets</a> -
      <a href="session">Manipuler les sessions</a>
      
      </div>

   
    
   </nav>-->