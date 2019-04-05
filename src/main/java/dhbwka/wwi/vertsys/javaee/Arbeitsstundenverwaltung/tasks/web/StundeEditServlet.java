/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.Arbeitsstundenverwaltung.tasks.web;

import dhbwka.wwi.vertsys.javaee.Arbeitsstundenverwaltung.common.web.WebUtils;
import dhbwka.wwi.vertsys.javaee.Arbeitsstundenverwaltung.common.web.FormValues;
import dhbwka.wwi.vertsys.javaee.Arbeitsstundenverwaltung.tasks.ejb.StundeBean;
import dhbwka.wwi.vertsys.javaee.Arbeitsstundenverwaltung.common.ejb.UserBean;
import dhbwka.wwi.vertsys.javaee.Arbeitsstundenverwaltung.common.ejb.ValidationBean;
import dhbwka.wwi.vertsys.javaee.Arbeitsstundenverwaltung.tasks.jpa.Stunde;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Seite zum Anlegen oder Bearbeiten einer Aufgabe.
 */
@WebServlet(urlPatterns = "/app/stunden/stunde/*")
public class StundeEditServlet extends HttpServlet {

    @EJB
    StundeBean stundeBean;

    @EJB
    UserBean userBean;

    @EJB
    ValidationBean validationBean;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        // Zu bearbeitende Stunden einlesen
        HttpSession session = request.getSession();

        Stunde stunde = this.getRequestedTask(request);
        request.setAttribute("edit", stunde.getId() != 0);
                                
        if (session.getAttribute("stunde_form") == null) {
            // Keine Formulardaten mit fehlerhaften Daten in der Session,
            // daher Formulardaten aus dem Datenbankobjekt übernehmen
            request.setAttribute("stunde_form", this.createStundeForm(stunde));
        }

        // Anfrage an die JSP weiterleiten
        request.getRequestDispatcher("/WEB-INF/stunden/stunde_edit.jsp").forward(request, response);
        
        session.removeAttribute("stunde_form");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Angeforderte Aktion ausführen
        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "save":
                this.saveStunde(request, response);
                break;
            case "delete":
                this.deleteStunde(request, response);
                break;
        }
    }

    /**
     * Aufgerufen in doPost(): Neue oder vorhandene Aufgabe speichern
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void saveStunde(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Formulareingaben prüfen
        List<String> errors = new ArrayList<>();

        
        String stundeDueDate = request.getParameter("stunde_due_date");
        String stundeDueTime1 = request.getParameter("stunde_due_time1");
        String stundeDueTime2 = request.getParameter("stunde_due_time2");
     

        Stunde stunde = this.getRequestedTask(request);

        Date dueDate = WebUtils.parseDate(stundeDueDate);
        Time dueTime1 = WebUtils.parseTime(stundeDueTime1);
        Time dueTime2 = WebUtils.parseTime(stundeDueTime2);

        if (dueDate != null) {
            stunde.setDueDate(dueDate);
        } else {
            errors.add("Das Datum muss dem Format dd.mm.yyyy entsprechen.");
        }

        if (dueTime1 != null) {
            stunde.setDueTime1(dueTime1);
        } else {
            errors.add("Die Uhrzeit muss dem Format hh:mm:ss entsprechen.");
        }
        
         if (dueTime2 != null) {
            stunde.setDueTime2(dueTime2);
        } else {
            errors.add("Die Uhrzeit muss dem Format hh:mm:ss entsprechen.");
        }
        
        this.validationBean.validate(stunde, errors);

        // Datensatz speichern
        if (errors.isEmpty()) {
            this.stundeBean.update(stunde);
        }

        // Weiter zur nächsten Seite
        if (errors.isEmpty()) {
            // Keine Fehler: Startseite aufrufen
            response.sendRedirect(WebUtils.appUrl(request, "/app/stunden/list/"));
        } else {
            // Fehler: Formuler erneut anzeigen
            FormValues formValues = new FormValues();
            formValues.setValues(request.getParameterMap());
            formValues.setErrors(errors);

            HttpSession session = request.getSession();
            session.setAttribute("stunde_form", formValues);

            response.sendRedirect(request.getRequestURI());
        }
    }

    /**
     * Aufgerufen in doPost: Vorhandene Aufgabe löschen
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void deleteStunde(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Datensatz löschen
        Stunde stunde = this.getRequestedTask(request);
        this.stundeBean.delete(stunde);

        // Zurück zur Übersicht
        response.sendRedirect(WebUtils.appUrl(request, "/app/stunden/list/"));
    }

    /**
     * Zu bearbeitende Aufgabe aus der URL ermitteln und zurückgeben. Gibt
     * entweder einen vorhandenen Datensatz oder ein neues, leeres Objekt
     * zurück.
     *
     * @param request HTTP-Anfrage
     * @return Zu bearbeitende Aufgabe
     */
    private Stunde getRequestedTask(HttpServletRequest request) {
        // Zunächst davon ausgehen, dass ein neuer Satz angelegt werden soll
        Stunde stunde = new Stunde();
        stunde.setOwner(this.userBean.getCurrentUser());
        stunde.setDueDate(new Date(System.currentTimeMillis()));
        stunde.setDueTime1(new Time(System.currentTimeMillis()));
        stunde.setDueTime2(new Time(System.currentTimeMillis()));

        // ID aus der URL herausschneiden
        String stundeId = request.getPathInfo();

        if (stundeId == null) {
            stundeId = "";
        }

        stundeId = stundeId.substring(1);

        if (stundeId.endsWith("/")) {
            stundeId = stundeId.substring(0, stundeId.length() - 1);
        }

        // Versuchen, den Datensatz mit der übergebenen ID zu finden
        try {
            stunde = this.stundeBean.findById(Long.parseLong(stundeId));
        } catch (NumberFormatException ex) {
            // Ungültige oder keine ID in der URL enthalten
        }

        return stunde;
    }

    /**
     * Neues FormValues-Objekt erzeugen und mit den Daten eines aus der
     * Datenbank eingelesenen Datensatzes füllen. Dadurch müssen in der JSP
     * keine hässlichen Fallunterscheidungen gemacht werden, ob die Werte im
     * Formular aus der Entity oder aus einer vorherigen Formulareingabe
     * stammen.
     *
     * @param stunde Die zu bearbeitende Aufgabe
     * @return Neues, gefülltes FormValues-Objekt
     */
    private FormValues createStundeForm(Stunde stunde) {
        Map<String, String[]> values = new HashMap<>();

        values.put("stunde_owner", new String[]{
            stunde.getOwner().getUsername()
        });

      
        values.put("stunde_due_date", new String[]{
            WebUtils.formatDate(stunde.getDueDate())
        });

        values.put("stunde_due_time1", new String[]{
            WebUtils.formatTime(stunde.getDueTime1())
        });
        
         values.put("stunde_due_time2", new String[]{
            WebUtils.formatTime(stunde.getDueTime2())
        });

        FormValues formValues = new FormValues();
        formValues.setValues(values);
        return formValues;
    }
}
