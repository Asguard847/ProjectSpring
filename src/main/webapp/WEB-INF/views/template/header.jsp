<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>AutoPark management system</title>


    <!-- Angular JS -->
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.1/angular.min.js"> </script>

    <%--JQuery--%>
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.4.min.js"></script>

    <%--Data Table--%>
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>

    <!-- Bootstrap core CSS -->
    <link  rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />" type="text/css">

    <!-- Main CSS -->
    <link rel="stylesheet" href="<c:url value="/css/main.css" />" type="text/css">

    <link href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" rel="stylesheet">





</head>
<!-- NAVBAR
================================================== -->
<body>
<div class="navbar-wrapper">
    <div class="container">

        <nav class="navbar navbar-inverse navbar-static-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                            aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>

                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                      <c:if test="${user.authority == 'ROLE_ADMIN'}">
                         <li><a href="<c:url value="/app/admin"/> "><spring:message code="header.admin"/></a></li>
                      </c:if>
                    </ul>
                    <ul class="nav navbar-nav pull-right">

                           <li><a><spring:message code="header.choose"/></a></li>
                           <li><a href="?locale=ru">rus</a></li>
                           <li><a href="?locale=en">en</a></li>
                        <c:if test="${user != null}">
                            <li><a href="<c:url value="/app/logout"/> "><fmt:message key = "header.logout"/></a></li>
                        </c:if>

                    </ul>
                </div>
            </div>
        </nav>

    </div>
</div>