<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@ attribute name="head" fragment="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="configuration.locale"/>
<!DOCTYPE html>
<html lang="${language}">
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery-1.9.1.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/bootstrap-theme.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/pageTamplate.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/table.css">
    <script src="${pageContext.request.contextPath}/resource/bootstrap/bootstrap.min.js"
            type="text/javascript"></script>
    <jsp:invoke fragment="head"/>
</head>
<body>
	<div id="pageheader" class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <jsp:invoke fragment="header"/>
    <div class="container">
        <div class="navbar-header">
            <c:if test="${not empty sessionScope.login}">
                   <a class="navbar-brand" href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message key="page.logout"/></a>
            </c:if>
            <c:if test="${sessionScope.login.role == 'admin'}">
                   <a class="navbar-brand" href="${pageContext.request.contextPath}/controller?command=searchform"><fmt:message key="admin.menu.search"/></a>                  
            </c:if>
             <c:if test="${sessionScope.login.role == 'admin'}">
                   <a class="navbar-brand" href="${pageContext.request.contextPath}/jsp/admin/students.jsp"><fmt:message key="admin.menu.students"/></a>                  
            </c:if>
            <c:if test="${sessionScope.login.role == 'admin'}">
                   <a class="navbar-brand" href="${pageContext.request.contextPath}/jsp/admin/admin.jsp"><fmt:message key="admin.menu.main"/></a>                  
            </c:if>
            <c:if test="${sessionScope.login.role == 'entrant'}">
                   <a class="navbar-brand" href="${pageContext.request.contextPath}/controller?command=unregisterform"><fmt:message key="menu.unregister"/></a>                  
            </c:if>
        </div>
        <div class="collapse navbar-collapse">

           
            <ul class="nav navbar-nav navbar-right" role="menu">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <c:choose>
                            <c:when test="${sessionScope.language == 'ru_RU'}">
                                <img src="${pageContext.request.contextPath}/resource/img/russia1.png"/>
                                ru
                            </c:when>
                            <c:otherwise>
                                <img src="${pageContext.request.contextPath}/resource/img/usa1.png"/>
                                en
                            </c:otherwise>
                        </c:choose>
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="${pageContext.request.contextPath}/controller?command=language&language=en_EN">
                                <img class="img-responsive flag"
                                     src="${pageContext.request.contextPath}/resource/img/usa1.png"/>
                                en
                            </a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/controller?command=language&language=ru_RU">
                                <p>
                                    <img class="img-responsive flag"
                                         src="${pageContext.request.contextPath}/resource/img/russia1.png"/>
                                    ru
                                </p>
                            </a>
                        </li>
                    </ul>
                </li>                
            </ul>
        </div>
    </div>


</div>

<div id="body" class="container">
    <div class="starter-template">
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-8">
                <jsp:doBody/>
            </div>
        </div>
    </div>
</div>
<div id="pagefooter" class="navbar-default">
    <jsp:invoke fragment="footer"/>
    <div class="navbar-inner">
        <div class="container">
            <span class="navbar-text">
                2014 &copy; <fmt:message key="page.copyright"/>
                </span>
        </div>
    </div>
</div>
</body>