<%@ page  contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
<c:set value="${pageContext.servletContext.contextPath}" var="contextURL"/>
<link rel="stylesheet" href="${contextURL}/css/cultuurhuis.css"/>
<title>Het CultuurHuis - reserveren</title>
</head>
<body>
 <vdab:header_reserveren/>
 <nav>
   <ul class="menu">
    <c:url value="/start.htm" var="startURL"/>
    <li><a href="${startURL}">Voorstellingen</a></li>
   </ul>
 </nav>
 <dl>
  <dt>Voorstelling:</dt>
  <dd class="boldmaroon"><c:out value='${voorstelling.titel}'/></dd>
  <dt>Uitvoerders:</dt>
  <dd class="boldmaroon"><c:out value='${voorstelling.uitvoerders}'/></dd>
  <dt>Datum:</dt>
  <dd class="boldmaroon"><fmt:formatDate value='${voorstelling.datum}' type="both" dateStyle="short" timeStyle="short"/></dd>
  <dt>Prijs:</dt>
  <dd class="boldmaroon">&euro;<fmt:formatNumber value='${voorstelling.prijs}' groupingUsed='false' minFractionDigits="2"/></dd>
  <dt>Vrije plaatsen:</dt>
  <dd class="boldmaroon">${voorstelling.vrijePlaatsen}</dd>
</dl>
 <c:url value="/reserveren.htm" var="action"><c:param name="voorstellingsnummer" value="${voorstelling.nummer}"/></c:url>
<form method="post" action="${action}" id="reservatieform">
  <label>Plaatsen:<span class="fout">${fouten.ongeldigeWaarde}</span>
  <input name= "plaatsen" type="number" value="${plaatsen}" maxLength="3" size="3" autofocus /></label><br/>
  <input type="submit" value="Reserveren" id="reservatieknop"/>
</form>
<script>
 document.getElementById('naamform').onsubmit = funtion(){
	 document.getElementById('onthoudknop').disable = true;
 };
</script>
</body>
</html>