<%-- 
    Document   : defaultMenu
    Created on : 8 nov. 2016, 21:03:18
    Author     : Vladimir
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="col-sm-2 sidenav well">
    <div class="well">


        <p><a href="personne-${sessionScope['user'].idPersonne}">My Profile</a></p>
        <img src="${pageContext.request.contextPath}/resources/images/ninja.png" class="img-circle" height="65" width="65" alt="Avatar">
    </div>
    <div class="well">


        <c:if test="${sessionScope['user'] != null}">
            <p>       ${sessionScope['user'].nom} ${sessionScope['user'].prenom} </p>


            <c:if test="${sessionScope['user'].profil == false }">
                <p>   Etudiant    </p>
            </c:if>
            <c:if test="${sessionScope['user'].profil == true }">
                <p>   Professeur    </p>
            </c:if>
            <jsp:useBean id="today" class="java.util.Date"/>   
            <c:if test="${sessionScope['user'].promotions.size() != 0}">
                <c:forEach items="${sessionScope['user'].promotions}" var="promotion">
                    <c:if test="${promotion.finDate.time gt today.time && promotion.debutDate.time lt today.time}">
                        <p>${promotion.name} </p>
                    </c:if>
                </c:forEach>
            </c:if>
        </c:if>

    </div>

</div>