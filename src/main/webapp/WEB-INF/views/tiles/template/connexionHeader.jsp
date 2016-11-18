<%-- 
    Document   : connexionHeader
    Created on : 18 nov. 2016, 01:55:31
    Author     : Vladimir
--%>
<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
     
      <a class="navbar-brand" href="#">Talha</a>
	  
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      
      <ul class="nav navbar-nav navbar-right">
	  
	  
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <c:if test="${sessionScope['user'] == null}">
          <form class="navbar-form navbar-right"  action="connexion" method="post">
            <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            <input class="form-control"  type="text" name="login" value="${login}" placeholder="Email Address"/>
             </div>
            <div class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
            <input class="form-control" type="password" name="password"  placeholder="Password"/>
            </div>
             <button type="submit" class="btn btn-primary">Login</button>
            <br/>${msgConnexion}
          </form>
        </c:if>
    </div>
      </ul>
    </div>
  </div>
</nav>

