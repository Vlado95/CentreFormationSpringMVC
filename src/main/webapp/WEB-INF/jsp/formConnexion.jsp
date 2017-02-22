<%-- 
    Document   : connection
    Created on : 18 nov. 2016, 00:04:58
    Author     : Vladimir
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.servletContext.contextPath}"
       scope="request" />
<!DOCTYPE html>
<html>
    <head>
        <title>connexion</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--<link rel="stylesheet" href="../resources/css/style.css">-->
        <style>

            body{
                padding:0;
                margin:0;
                background: linear-gradient(to bottom, #68EACC 0%, #497BE8 100%);
            }
            .vid-container{
                position:relative;
                height:100vh;
                overflow:hidden;
            }
            .bgvid.back {
                position: fixed; right: 0; bottom: 0;
                min-width: 100%; min-height: 100%;
                width: auto; height: auto; z-index: -100;
            }
            .inner {
                position: absolute;
            }
            .inner-container{
                width:400px;
                height:400px;
                position:absolute;
                top:calc(50vh - 200px);
                left:calc(50vw - 200px);
                overflow:hidden;
            }
            .bgvid.inner{
                top:calc(-50vh + 200px);
                left:calc(-50vw + 200px);
                filter: url("data:image/svg+xml;utf9,<svg%20version='1.1'%20xmlns='http://www.w3.org/2000/svg'><filter%20id='blur'><feGaussianBlur%20stdDeviation='10'%20/></filter></svg>#blur");
                -webkit-filter:blur(10px);
                -ms-filter: blur(10px);
                -o-filter: blur(10px);
                filter:blur(10px);
            }
            .box{
                position:absolute;
                height:100%;
                width:100%;
                font-family:Helvetica;
                color:#fff;
                background:rgba(0,0,0,0.13);
                padding:30px 0px;
            }
            .box h1{
                text-align:center;
                margin:30px 0;
                font-size:30px;
            }
            .box input{
                display:block;
                width:300px;
                margin:20px auto;
                padding:15px;
                background:rgba(0,0,0,0.2);
                color:#fff;
                border:0;
            }
            .box input:focus,.box input:active,.box button:focus,.box button:active{
                outline:none;
            }
            .box button{
                background:#742ECC;
                border:0;
                color:#fff;
                padding:10px;
                font-size:20px;
                width:330px;
                margin:20px auto;
                display:block;
                cursor:pointer;
            }
            .box button:active{
                background:#27ae60;
            }
            .box p{
                font-size:14px;
                text-align:center;
            }
            .box p span{
                cursor:pointer;
                color:#666;
            }
        </style>
    </head>
    <body>
        <div class="vid-container">
             <li><a href="reg-new-user">nouveau utilisateur</a></li>
            <div class="inner-container">
                <div class="box">
                    <h1>Connexion</h1>
                    <c:if test="${sessionScope['user'] == null}">
                        <form  action="connexion" method="post">
                            <div >

                                <input  type="text" name="login" value="${login}" placeholder="Email Address"/>
                            </div>
                            <div >
                                <input type="password" name="password"  placeholder="Password"/>
                            </div>
                            <button type="submit" >Connecter</button>
                            <br/>${msgConnexion}
                        </form>
                    </c:if>
                </div>
            </div>
        </div>
    </body>

</html>
