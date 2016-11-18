<%-- 
    Document   : defaultLayout
    Created on : 8 nov. 2016, 20:57:55
    Author     : Vladimir
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}"
       scope="request" />
<!--    <link href="<c :url value="/resources/css/main.css" />" rel="stylesheet">
    <script src="<c :url value="/resources/js/jquery.1.10.2.min.js" />"></script>-->
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title><tiles:getAsString name="title" /></title>
        <link rel='stylesheet' href='webjars/bootstrap/3.3.7/css/bootstrap.min.css'>
        <script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
        <script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="${cp}/resources/js/main.js"></script>
        <link rel="stylesheet" type="text/css" href="${cp}/resources/css/site.css"/>

    </head>

    <body>

        <div  >
            <div id="header">
                <header >
                    <%--<c:if test="${sessionScope['user'] != null}">--%>
                    <tiles:insertAttribute name="header" />
                    <%--</c:if>--%>
                   <%--<c:if test="${sessionScope['user'] == null}">--%>
                    <%--<tiles:insertAttribute name="connexionHeader" />--%>
                    <%--</c:if>--%> 
                </header>
            </div>
            <div class=" text-center">
                <div class="row content">
                    <div id="menu">
                        <section >
                            <tiles:insertAttribute name="menu" />
                        </section>
                    </div>
                    <div class=""id="main"> 
                        <section>
                            <tiles:insertAttribute name="body" />
                        </section>
                    </div>
                </div>
            </div>
            <div id="footer"> 
                <footer>
                    <tiles:insertAttribute name="footer" />
                </footer>
            </div>
        </div>

    </body>
</html>