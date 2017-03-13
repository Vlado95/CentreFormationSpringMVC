<%@tag description="En-tête des pages" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}" scope="request" />

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="title" required="true" description="titre de la page" %>
<ul class="nav navbar-nav">

    <li class="active"><a href=".">Acceuil</a></li>
    <li><a href="projet">Liste de projets</a></li>
    <li> 
        <%--<sec:authorize access="hasRole('Professeur')">--%>
        <sec:authorize url="/new-projet">
         <a class="text-left" href="#" data-toggle="modal" data-target="#dialog" data-url="new-projet"><span class="glyphicon glyphicon-plus"></span> Creér un nouveau projet</a>
        </sec:authorize> 
    </li>
    <li><a href="session">Manipuler les sessions</a></li>
</ul>
<ul class="navbar-form nav navbar-nav navbar-right">
    <li>   
        <%--<c:if test="${sessionScope['user'] == null}">--%>
        <!--<form style="float: right" action="connexion" method="post">-->
        <!--Login :-->
        <!--<input type="text" name="login" value="$ {login}"/>-->
        <!--Mot de passe :-->
        <!--<input type="password" name="password" size="12"/>-->
        <!--<button type="submit">Connecter</button>-->
        <!--<br/>-->
        <!--$ { msgConnexion}-->
        <!--</form>-->
        <%--</c:if>--%>
        <%--<c:if test="${sessionScope['user'] != null}">--%>
        <!--<form action="deconnexion" method="post">-->
        <!--<p><a href="#">$ {sessionScope['user'].nom}</a>--> 
        <!--<button type="submit" class="btn btn-default btn-sm">-->
        <!--<span class="glyphicon glyphicon-log-out"></span> Log out-->
        <!--</button>-->
        <!--</p>-->

        <!--</form>-->
        <%--</c:if>--%>
        <c:url var="logoutUrl" value="/logout"/>
        <form class="form-inline" action="${logoutUrl}" method="post">
<!--            <input type="submit" value="Log out" />-->
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <p><a href="#">${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.nom}</a>
        <button type="submit" class="btn btn-default btn-sm">
        <span class="glyphicon glyphicon-log-out"></span> Log out
        </button>
        </form>

</ul>