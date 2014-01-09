<%@ page  contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="vdab" uri="http://vdab.be/tags" %>
<!DOCTYPE HTML>
<html>
<head>
<c:set value="${pageContext.servletContext.contextPath}" var="contextURL"/>
<link rel="stylesheet" href="${contextURL}/css/cultuurhuis.css"/>
<title>Het CultuurHuis</title>
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
</body>
</html>