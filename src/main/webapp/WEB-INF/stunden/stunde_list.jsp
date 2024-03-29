<%-- 
    Copyright © 2018 Dennis Schulmeister-Zimolong

    E-Mail: dhbw@windows3.de
    Webseite: https://www.wpvs.de/

    Dieser Quellcode ist lizenziert unter einer
    Creative Commons Namensnennung 4.0 International Lizenz.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib tagdir="/WEB-INF/tags/templates" prefix="template"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<template:base>
    <jsp:attribute name="title">
        Liste der Stunden
    </jsp:attribute>

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/stunde_list.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="menu">
        <div class="menuitem">
            <a href="<c:url value="/app/dashboard/"/>">Dashboard</a>
        </div>

        <div class="menuitem">
            <a href="<c:url value="/app/stunden/stunde/new/"/>">Arbeitsstunden eintragen</a>
        </div>
    </jsp:attribute>

    <jsp:attribute name="content">
                <table>
                    <thead>
                        <tr>
                            
                            <th>Mitarbeitername</th>
                            <th>Datum</th>
                            <th>angefangen um</th>
                            <th>aufgehört um</th>
                           
                        </tr>
                    </thead>
                    <c:forEach items="${stunden}" var="stunde">
                            <td>
                                <a href="<c:url value="/app/stunden/stunde/${stunde.id}/"/>">
                                    <c:out value="${stunde.owner.username}"/>
                                </a>
                            <td>
                                <c:out value="${stunde.dueDate}"/> 
                            </td>
                            <td>
                                <c:out value="${stunde.dueTime1}"/>
                            </td>
                            <td>
                                <c:out value="${stunde.dueTime2}"/>
                            </td> 
                          
                            </tr>
                        
                    </c:forEach>
                </table>
            <%--</c:otherwise>
        </c:choose>--%>
    </jsp:attribute>
</template:base>