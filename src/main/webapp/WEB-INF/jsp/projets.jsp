<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<p:header title="Projets"/>
<h1>Liste de projets</h1>
<div class="col-sm-8 ">
    <div class="text-right">
        <sec:authorize access="hasRole('Professeur')">
            <a class="text-left" href="#" data-toggle="modal" data-target="#dialog" data-url="new-projet"><span class="glyphicon glyphicon-plus"></span> Creér un nouveau projet</a>
        </sec:authorize>
    </div>
    <table class="table table-bordered table-curved well">
        <thead>
            <tr class="label-default" style="color: white;">
                <th>Projets</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>

            <c:if test="${message ==null}">
                <c:if test="${projets.size() != 0}">
                <ol>
                    <c:forEach items="${projets}" var="projet">
                        <tr>    
                            <td> 
                                <a href="projet-${projet.getId()}">${projet.getTitre()}</a>
                            </td>
                            <td>   
                        <sec:authorize access="hasRole('Professeur')">
                            <c:if test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.idPersonne == projet.createur.idPersonne}">
                                <a href="#" data-toggle="modal" data-target="#dialog" data-url="projet-${projet.getId()}-modifier">
                                    <span class="glyphicon glyphicon-pencil"></span>
                                </a>
                            </c:if>
                        </sec:authorize>
                        </td>

                        </tr>
                    </c:forEach>
                </ol>
            </c:if>

            </tbody>
        </table>  
    </c:if>
    <c:if test="${message != null}">
        <h1>${message}</h1>
    </c:if>

</div>  

<div id="dialog" class="modal"><div class="modal-dialog"><div class="modal-content"></div></div></div>