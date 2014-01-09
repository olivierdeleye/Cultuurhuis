<%@ page  contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
<c:set value="${pageContext.servletContext.contextPath}" var="contextURL"/>
<link rel="stylesheet" href="${contextURL}/css/cultuurhuis.css"/>
<title>Het CultuurHuis - Bevestiging reservaties</title>
</head>
<body>
<vdab:header_overzicht/>
 <nav>
   <ul class="menu">
    <c:url value="/start.htm" var="startURL"/>
    <li><a href="${startURL}">Voorstellingen</a></li>
   </ul>
 </nav>
 <h2>Gelukte reserveringen</h2>
 <c:if test="${not empty mandjeItemsGelukt}">
 <table>
   <thead>
     <tr class="even">
       <th>Datum</th><th>Titel</th><th>Uitvoerders</th><th>Prijs</th><th>Plaatsen</th>
     </tr>
    </thead>
    <c:forEach var="entry" items="${mandjeItemsGelukt}" varStatus="status" >
    <tbody>
    <tr class="${status.count % 2 == 0? 'even':'oneven'}">
     <td><fmt:formatDate value='${entry.voorstelling.datum}' type="both" dateStyle="short" timeStyle="short"/></td>
     <td><c:out value='${entry.voorstelling.titel}'/></td>
     <td><c:out value='${entry.voorstelling.uitvoerders}'/></td>
     <td>&euro;<fmt:formatNumber value='${entry.voorstelling.prijs}' groupingUsed='false' minFractionDigits="2"/></td>
     <td class="right">${entry.plaatsen}</td>
   </tr>
   </tbody>
 </c:forEach>
</table>
</c:if>
<h2>Mislukte reserveringen</h2>
<c:if test="${not empty mandjeItemsMislukt}">
<table>
 <thead>
   <tr class="even">
     <th>Datum</th><th>Titel</th><th>Uitvoerders</th><th>Prijs</th><th>Plaatsen</th>
   </tr>
 </thead>
  <c:forEach var="entry" items="${mandjeItemsMislukt}" varStatus="status" >
  <tbody>
   <tr class="${status.count % 2 == 0? 'even':'oneven'}">
    <td>${entry.voorstelling.datum}</td>
    <td><c:out value='${entry.voorstelling.titel}'/></td>
    <td><c:out value='${entry.voorstelling.uitvoerders}'/></td>
    <td>&euro;<fmt:formatNumber value='${entry.voorstelling.prijs}' groupingUsed='false' minFractionDigits="2"/></td>
    <td class="right">${entry.plaatsen}</td>
  </tr>
  </tbody>
 </c:forEach>
</table>
</c:if>
</body>
</html>