<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>
<!DOCTYPE HTML>
<c:set value="${pageContext.servletContext.contextPath}" var="contextURL"/>
<html lang="nl">
<head>
<title>Het Cultuurhuis - Interne Serverfout</title>
<link rel="stylesheet" href="${contextURL}/css/cultuurhuis.css"/>
</head>
<body>
<vdab:header_voorstellingen/>
<c:url value="/start.htm" var="startURL"/>
<ul class="menu">
 <li><a href="${startURL}">Voorstellingen</a><li>
</ul>
<h1>Problemen bij het ophalen van data</h1>
<img alt="data fout" src="${contextURL}/img/datafout.jpg">
<p>We kunnen de gevraagde data niet ophalen wegens een technische storing.<br/>
   Gelieve de helpdesk te contacteren</p>
</body>
</html>