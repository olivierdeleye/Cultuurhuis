<%@ page  contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
<c:set value="${pageContext.servletContext.contextPath}" var="contextURL"/>
<link rel="stylesheet" href="${contextURL}/css/cultuurhuis.css"/>
<title>Het CultuurHuis - Voorstellingen</title>
</head>
<body>
<vdab:header_voorstellingen/>
<c:if test="${not empty sessionScope.mandje}">
 <ul class="menu">
<c:url value="/mandje.htm" var="mandjeURL"/>
  <li><a href="${mandjeURL}">Reservatiemandje</a></li>
<c:url value="/bevestiging.htm" var="bevestigingURL"/>
  <li><a href="${bevestigingURL}">Bevestiging reservaties</a><li>
  </ul>
</c:if>
 <h2>Genres</h2>
    <nav>
      <ul class="menu"> <!--  TODO extreme makeover -->
       <c:forEach var="item" items="${genres}">
        <c:url value="/voorstelling.htm" var="voorstellingURL"><c:param name="genre" value="${item}"/></c:url>
        <li><a href="${voorstellingURL}">${item}</a></li>
       </c:forEach>
      </ul>
    </nav>
<h2>${genre} voorstellingen</h2>
<table>
   <thead>
     <tr>
       <th>Datum</th><th>Titel</th><th>Uitvoerders</th><th>Prijs</th><th>Vrije plaatsen</th><th>Reserveren</th>
     </tr>
    </thead>
    <c:forEach var="voorstelling" items="${voorstellingen}" varStatus="status" >
    <tbody>
    <tr class="${status.count % 2 == 0? 'even':'oneven'}">
     <td><fmt:formatDate value='${voorstelling.datum}' type="both" dateStyle="short" timeStyle="short"/></td>
     <td><c:out value='${voorstelling.titel}'/></td>
     <td><c:out value='${voorstelling.uitvoerders}'/></td>
     <td>&euro;<fmt:formatNumber value='${voorstelling.prijs}' groupingUsed='false' minFractionDigits="2"/></td>
     <td>${voorstelling.vrijePlaatsen}</td>
     <td><c:if test="${voorstelling.vrijePlaatsen > 0}">
       <c:url value="/reserveren.htm" var ="reserverenURL">
          <c:param name="nummer" value="${voorstelling.nummer}"/>
        </c:url>
       <a href="<c:out value='${reserverenURL}'/>">Reserveren</a>
         </c:if>
     </td>
     </tr>
     </tbody>
 </c:forEach>
</table>
</body>
</html>