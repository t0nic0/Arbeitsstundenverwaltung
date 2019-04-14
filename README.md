Beispielanwendung "Arbeitsstundenverwaltung"
=========================

Kurzbeschreibung
----------------

Dies ist ein Beispiel für eine in Java realisierte, serverseitige MVC-Webanwendung.
Die Anwendung setzt dabei ganz klassisch auf der „Jakarta Enterprise Edition”
(ehemals „Java Enterprise Edition“) auf und läuft daher in einem speziell dafür
ausgelegten Applikationsserver. Sämtliche Anwendungslogik wird dabei vom Server
implementiert, so dass für jedes URL-Pattern der Anwendung ein komplett serverseitig
generierte HTML-Seite abgerufen und im Browser dargestellt wird.

Verwendete Technologien
-----------------------

Die App nutzt Maven als Build-Werkzeug und zur Paketverwaltung. Auf diese Weise
werden die für Jakarta EE notwendigen APIs, darüber hinaus aber keine weiteren
Abhängigkeiten, in das Projekt eingebunden. Der Quellcode der Anwendung ist dabei
wie folgt strukturiert:

 * **Servlets** dienen als Controller-Schicht und empfangen sämtliche HTTP-Anfragen.
 * **Enterprise Java Beans** dienen als Model-Schicht und kapseln die fachliche Anwendungslogik.
 * **Persistence Entities** modellieren das Datenmodell und werden für sämtliche Datenbankzugriffe genutzt.
 * **Java Server Pages** sowie verschiedene statische Dateien bilden die View und generieren den
   auf dem Bildschirm angezeigten HTML-Code.

Folgende Entwicklungswerkzeuge kommen dabei zum Einsatz:

 * [NetBeans:](https://netbeans.apache.org/) Integrierte Entwicklungsumgebung für Java und andere Sprachen
 * [Maven:](https://maven.apache.org/) Build-Werkzeug und Verwaltung von Abhängigkeiten
 * [Git:](https://git-scm.com/") Versionsverwaltung zur gemeinsamen Arbeit am Quellcode
 * [TomEE:](https://tomee.apache.org/) Applikationsserver zum lokalen Testen der Anwendung
 * [Derby:](https://db.apache.org/derby/) In Java implementierte SQL-Datenbank zum Testen der Anwendung

Screenshots
-----------

<table style="max-width: 100%;">
    <tr>
        <td>
            <a href="screenshot1.PNG">
                <img src="screenshot1.PNG" style="display: block; width: 100%;" />
            </a>
        </td>
        <td>
            <a href="screenshots2.PNG">
                <img src="screenshots2.PNG" style="display: block; width: 100%;" />
            </a>
        </td>
    </tr>
    <tr>
        <td>
            Login
        </td>
        <td>
            Registrierung
        </td>
    </tr>
</table>

<table style="max-width: 100%;">
    <tr>
        <td>
            <a href="screenshot3.PNG ">
                <img src="screenshot3.PNG" style="display: block; width: 100%;" />
            </a>
        </td>
        <td>
            <a href="screenshot4.PNG">
                <img src="screenshot4.PNG" style="display: block; width: 100%;" />
            </a>
        </td>
    </tr>
    <tr>
        <td>
            Dashboard
        </td>
        <td>
            Liste mit Aufgaben
        </td>
    </tr>
</table>
<table style="max-width: 100%;">
    <tr>
        <td>
            <a href="screenshot5.PNG">
                <img src="screenshot5.PNG" style="display: block; width: 100%;" />
            </a>
        </td>
        <td>
            <a href="screenshot6.PNG ">
                <img src="screenshot6.PNG" style="display: block; width: 100%;" />
            </a>
        </td>
    </tr>
    <tr>
        <td>
            Aufgabe bearbeiten
        </td>
        <td>
            Kategorien bearbeiten
        </td>
    </tr>
</table>

Copyright
---------

Dieses Projekt ist lizenziert unter
[_Creative Commons Namensnennung 4.0 International_](http://creativecommons.org/licenses/by/4.0/)

© 2018 – 2019 Dennis Schulmeister-Zimolong <br/>

E-Mail: [dhbw@windows3.de](mailto:dhbw@windows3.de) <br/>
Webseite: https://www.wpvs.de
