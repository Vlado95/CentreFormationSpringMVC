<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<p:header title="Projets"/>
        <c:if test="${message ==null}">
            <c:if test="${projets.size() != 0}">
                <h1>Projets</h1>
                <ol>
                    <c:forEach items="${projets}" var="projet">
                        <li><a href="projet-${projet.getId()}">${projet.getTitre()}</a></li>
                        </c:forEach>
                </ol>
            </c:if>
            <c:if test="${projets.size() ==0}">
                <h1 style="color: red">Pas de projets</h1>
            </c:if>
        </c:if>
        <c:if test="${message != null}">
            <h1>${message}</h1>
        </c:if>
