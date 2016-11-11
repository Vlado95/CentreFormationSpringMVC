<%-- 
    Document   : defaultHeader
    Created on : 8 nov. 2016, 21:03:02
    Author     : Vladimir
--%>

<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>

<!--<nav class="navbar navbar-inverse " role="navigation">
    
    <div class="container">
        <p : header title="$ {titre}"/>
       
    </div>
</nav>
        -->
        
        
<nav class="navbar  navbar-inverse">
  <div class=" container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Logo</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
        <p:header title="${titre}"/>

    </div>
  </div>
</nav>