<%-- 
    Document   : defaultMenu
    Created on : 8 nov. 2016, 21:03:18
    Author     : Vladimir
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="col-md-2 col-lg-2 col-sm-2 sidenav well">
    <div class="well">


        <p><a href="personne-${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.idPersonne}">My Profile</a></p>
        <img src="${pageContext.request.contextPath}/resources/images/ninja.png" class="img-circle" height="65" width="65" alt="Avatar">
    </div>
    <div class="well">
        <c:if test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal != null}">
            <p>${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.nom} 
                ${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.prenom} </p>
            <p>${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.role }</p>
            <p>${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.promotion} </p>

        </c:if>

    </div>

</div>