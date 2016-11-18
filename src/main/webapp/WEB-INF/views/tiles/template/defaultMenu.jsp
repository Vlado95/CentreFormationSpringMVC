<%-- 
    Document   : defaultMenu
    Created on : 8 nov. 2016, 21:03:18
    Author     : Vladimir
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- <div style="padding: 5px;">
<nav>
    
    <ul id="menu">
        <li><a href="{pageContext.request.contextPath}/">Home</a></li>
      <li><a href="{pageContext.request.contextPath}/products">Products</a></li>
       <li><a href="{pageContext.request.contextPath}/contactus">Contact Us</a></li>
    </ul>
</nav>
</div>    -->
<!--   
 <div >
    <div class="col-sm-20 col-md-2 affix-sidebar">
		<div 
  <div class="navbar navbar-default">
    <div >
      <ul >
          <li><a href="${pageContext.request.contextPath}/"><span class="glyphicon "></span> Home mmmm </a></li>
        <li><a href="${pageContext.request.contextPath}/products"><span class="glyphicon "></span> Product</a></li>
        <li><a href="${pageContext.request.contextPath}/contactus"><span class="glyphicon "></span> Contact us</a></li>
      </ul>
      </div>/.nav-collapse 
    </div>
  </div>
	</div>-->


    <!--<div class="col-sm-3 well">-->
        <div class="col-sm-2 sidenav well">
      <div class="well">
          

        <p><a href="#">My Profile</a></p>
        <img src="${pageContext.request.contextPath}/resources/images/ninja.png" class="img-circle" height="65" width="65" alt="Avatar">
      </div>
      <div class="well">
          
          
          <c:if test="${sessionScope['user'] != null}">
              <p>       ${sessionScope['user'].nom} ${sessionScope['user'].prenom} </p>
              
              <c:if test="${sessionScope['user'].profil  == true} ">
                   <p>   Professeur    </p>
              </c:if>
                                
              <c:if test="${sessionScope['user'].profil == false }">
                 <p>   Etudiant    </p>
              </c:if>
              <%--<c:forEach  items="${sessionScope['user'].getPromotions()}" var="promotion">--%>
                  <!--<p> promotion.getId()</p>-->
<!--             <p> $ {sessionScope['user'].getPromotions().getId()}</p>-->
              <%--</c:forEach>--%>
        </c:if>

      </div>
      
    </div>