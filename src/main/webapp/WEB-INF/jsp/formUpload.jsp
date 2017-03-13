<%-- 
    Document   : Upload
    Created on : 22 nov. 2016, 00:06:53
    Author     : Vladimir
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="p" tagdir="/WEB-INF/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>


<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h4 class="modal-title">${titre}</h4>
</div>
<div class="modal-body"> 
    <%--<form:form method="post"  enctype="multipart/form-data" >--%>
    <form method="post" action="${action}" enctype="multipart/form-data">
        <table border="0">


            <tr>
                <td>Fichier:</td>
                <td><input type="file"  name="fileUpload" size="50" /></td>
            </tr>
            <!--                <tr>
                                <td>Pick file #2:</td>
                                <td><input type="file" name="fileUpload" size="50" /></td>
                            </tr>-->

            <tr>
                <td>Description:</td>
                <td><textarea rows="2" cols="50" name="description" ></textarea></td>
            </tr>


            <!--                <tr>
                                <td colspan="2" align="center"><button type="submit">${action}</button></td>
                            </tr>-->
        </table><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="modal-footer">
            <button type="submit" class="btn btn-default">${action}</button>
            <button type="button" class="btn btn-default" data-dismiss="modal">Fermer</button>
        </div> 
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>

    <%--</form:form>--%>
</div>


