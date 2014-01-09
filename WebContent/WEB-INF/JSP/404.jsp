<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>
<!DOCTYPE HTML>
<c:set value="${pageContext.servletContext.contextPath}" var="contextURL"/>
<html lang="nl">
<head>
<title>Het CultuurHuis</title>
<link rel="stylesheet" href="${contextURL}/css.cultuurhuis.css"/>
</head>
<body>
<vdab:header_voorstellingen/>
<c:url value="/start.htm" var="startURL"/>
<ul class="menu">
 <li><a href="${startURL}">Voorstellingen</a><li>
</ul>
<h1>Pagina niet gevonden</h1>
<img alt="fout" src="${contextURL}/img/fout.jpg">
<p>De pagina die u zocht staat niet op onze website.</p>
</body>  
</html>