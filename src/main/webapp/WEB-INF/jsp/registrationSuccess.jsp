<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<h2>Spring's form tags example</h2>

<table>
<tr>
<td>Nom :</td><td>${user.nom}</td>
</tr>
<tr>
<td>prenom :</td><td>${user.prenom}</td>
</tr>
<tr>
<td>email :</td><td>${user.email}</td>
</tr>
<tr>
<td>Confirm Email :</td><td>${user.confirmEmail}</td>
</tr>
<tr>
<td>Password :</td><td>${user.password}</td>
</tr>
<tr>
<td>Confirm Password :</td><td>${user.confirmPassword}</td>
</tr>
</table>
</body>
</html>