<%-- 
    Document   : personnes
    Created on : 20 nov. 2016, 23:43:05
    Author     : Vladimir
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<p:header title="Personnes"/>
<h1>Liste de personnes</h1>
<div class="col-sm-8 ">
    <table class="table table-bordered table-curved well">
        <thead>
            <tr class="label-default" style="color: white;">
                <th>Personnes</th>
            </tr>
        </thead>
        <tbody>

            <c:if test="${message ==null}">
                <c:if test="${personnes.size() != 0}">
                <ol>
                    <c:forEach items="${personnes}" var="personne">
                        <tr>    
                            <td> 
                                <a href="personne-${personne.idPersonne}">${personne.getNom()}</a>
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
