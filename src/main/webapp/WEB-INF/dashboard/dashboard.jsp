<%-- 
    Copyright © 2019 Dennis Schulmeister-Zimolong

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
        Dashboard
    </jsp:attribute>

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/dashboard.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="menu">
        <div class="menuitem">
            <a href="<c:url value="/app/tasks/list/"/>">Liste</a>
        </div>

        <div class="menuitem">
            <a href="<c:url value="/app/tasks/task/new/"/>">Arbeitsstunde eintragen</a>
        </div>
    </jsp:attribute>

    <jsp:attribute name="content">
        <c:choose>
            <c:when test="${empty sections}">
                <p>
                    Es wurden keine Dashboard-Kacheln gefunden. 🙈
                </p>
            </c:when>
            <c:otherwise>
                <jsp:useBean id="utils" class="dhbwka.wwi.vertsys.javaee.Arbeitsstundenverwaltung.common.web.WebUtils"/>

                <%--
                    <div class="tile ${tile.cssClass}">
                         <c:choose>
                             <c:when test="${empty sections}">
                                <a href="<c:url value="${tile.href}"/>">
                                    <div class="content">
                                        <div class="label">
                                            <label> monatlich </label>
                                        </div>
                                    </div>
                                </a>
                            </c:when>
                        <c:otherwise>
                    </div>    
                    <div class="tile ${tile.cssClass}">
                        <a name ="weekly" href="<c:url value="${tile.href}"/>">
                            <div class="content">
                                <div class="label">
                                   <label> wochentlich </label>
                                </div>
                            </div>
                        </a>  
                            </c:otherwise>
                        </c:choose>
                    </div>
                --%>        


                <c:forEach items="${sections}" var="section">
                    <h2>
                        <c:out value="${section.label}"/>
                    </h2>

                    <c:forEach items="${section.tiles}" var="tile">
                        <div class="tile ${tile.cssClass}">
                            <c:choose>
                                <c:when test="!empty tile.href">
                                    <a href="<c:url value="${tile.href}"/>">
                                        <div class="content">
                                            <div class="label">
                                                <c:out value="${tile.label}"/>
                                            </div>
                                            <div class="icon icon-${tile.icon}"></div>
                                            <div class="amount">
                                                <c:choose>
                                                    <c:when test="${tile.showDecimals}">
                                                        <c:out value="${utils.formatDouble(tile.amount)}"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:out value="${utils.formatInteger(tile.amount)}"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                        </div>
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <div class="content">
                                        <div class="label">
                                            <c:out value="${tile.label}"/>
                                        </div>
                                        <div class="icon icon-${tile.icon}"></div>
                                        <div class="amount">
                                            <c:choose>
                                                <c:when test="${tile.showDecimals}">
                                                    <c:out value="${utils.formatDouble(tile.amount)}"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:out value="${utils.formatInteger(tile.amount)}"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </c:forEach>
                </c:forEach>


            </c:otherwise>
        </c:choose>
    </jsp:attribute>
</template:base>