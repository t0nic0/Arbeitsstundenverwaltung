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
        <c:choose>
            <c:when test="${edit}">
                Arbeitsstunden bearbeiten
            </c:when>
            <c:otherwise>
                Arbeitsstunden eintragen
            </c:otherwise>
        </c:choose>
    </jsp:attribute>

    <jsp:attribute name="head">
        <link rel="stylesheet" href="<c:url value="/css/stunde_edit.css"/>" />
    </jsp:attribute>

    <jsp:attribute name="menu">
        <div class="menuitem">
            <a href="<c:url value="/app/dashboard/"/>">Dashboard</a>
        </div>
        
        <div class="menuitem">
            <a href="<c:url value="/app/stunden/list/"/>">Liste</a>
        </div>
    </jsp:attribute>

    <jsp:attribute name="content">
        <form method="post" class="stacked">
            <div class="column">
                <%-- CSRF-Token --%>
                <input type="hidden" name="csrf_token" value="${csrf_token}">

                <%-- Eingabefelder --%>
                <label for="stunde_owner">Mitarbeitername:</label>
                <div class="side-by-side">
                    <input type="text" name="stunde_owner" value="${stunde_form.values["stunde_owner"][0]}" readonly="readonly">
                </div>

                <label for="stunde_due_date">
                    Datum:
                    <span class="required">*</span>
                </label>
                <div class="side-by-side">
                    <input type="text" name="stunde_due_date" value="${stunde_form.values["stunde_due_date"][0]}">
                </div>
                
                <label for="stunde_due_date">
                    angefangen um:
                <span class="required">*</span>
                <div class="side-by-side">
                    <input type="text" name="stunde_due_time1" value="${stunde_form.values["stunde_due_time1"][0]}">
                </div>
                </label>
                
                <label for="stunde_due_date">
                    aufgehört um:
                <span class="required">*</span>
                <div class="side-by-side">
                    <input type="text" name="stunde_due_time2" value="${stunde_form.values["stunde_due_time2"][0]}">
                </div>
                </label>
           
                <%-- Button zum Abschicken --%>
                <div class="side-by-side">
                    <button class="icon-pencil" type="submit" name="action" value="save">
                        Sichern
                    </button>

                    <c:if test="${edit}">
                        <button class="icon-trash" type="submit" name="action" value="delete">
                            Löschen
                        </button>
                    </c:if>
                </div>
            </div>

            <%-- Fehlermeldungen --%>
            <c:if test="${!empty stunde_form.errors}">
                <ul class="errors">
                    <c:forEach items="${stunde_form.errors}" var="error">
                        <li>${error}</li>
                    </c:forEach>
                </ul>
            </c:if>
        </form>
    </jsp:attribute>
</template:base>