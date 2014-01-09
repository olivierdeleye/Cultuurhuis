<%@ page  contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
<c:set value="${pageContext.servletContext.contextPath}" var="contextURL"/>
<link rel="stylesheet" href="${contextURL}/css/cultuurhuis.css"/>
<title>Het CultuurHuis - reservatiemandje</title>
</head>
<body>
 <vdab:header_reservatiemandje/>
 <nav>
   <ul class="menu">
    <c:url value="/start.htm" var="startURL"/>
    <li><a href="${startURL}">Voorstellingen</a></li>
    <c:url value="/bevestiging.htm" var="bevestigingURL"/>
    <li><a href="${bevestigingURL}">Bevestiging reservaties</a></li>
   </ul>
 </nav>
 <c:if test="${not empty sessionScope.mandje}">
 <c:url value="/mandje.htm" var="mandjeURL"/>
 <form  action="${mandjeURL}" method="post" id="verwijderform">
 <c:set var= "som" value="${0}"/> <!--  TODO  berekening in servlet -->
 <table>
   <thead>
     <tr class="even">
       <th>Datum</th><th>Titel</th><th>Uitvoerders</th><th>Prijs</th><th>Plaatsen</th><th><input type="submit" value="Verwijderen" id="verwijderknop"></th>
     </tr>
    </thead>
    <c:forEach var="entry" items="${mandjeItems}" varStatus="status" >
    
    <tbody>
    <tr class="${status.count % 2 == 0? 'even':'oneven'}">
     <td>${entry.voorstelling.datum}</td>
     <td><c:out value='${entry.voorstelling.titel}'/></td>
     <td><c:out value='${entry.voorstelling.uitvoerders}'/></td>
     <td>&euro;<fmt:formatNumber value='${entry.voorstelling.prijs}' groupingUsed='false' minFractionDigits="2"/></td>
     <td class="right">${entry.plaatsen}</td>
     <td class="center">
      <input type="checkbox" name="nummer" value="${entry.voorstelling.nummer}"/>
     </td>
     </tr>
   </tbody>
 </c:forEach>
</table><br>
 </form>
  <script>
     document.getElementById('verwijderform').onsubmit = funtion(){
	     document.getElementById('verwijderknop').disable = true;
     };
  </script>
 <section>Te betalen: &euro;<fmt:formatNumber value='${totaalprijs}' groupingUsed='false' minFractionDigits="2"/> </section>
 </c:if>
</body>
</html>