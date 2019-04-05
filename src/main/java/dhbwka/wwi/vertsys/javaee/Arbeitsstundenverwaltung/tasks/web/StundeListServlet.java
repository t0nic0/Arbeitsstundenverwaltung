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

import dhbwka.wwi.vertsys.javaee.Arbeitsstundenverwaltung.common.ejb.UserBean;
import dhbwka.wwi.vertsys.javaee.Arbeitsstundenverwaltung.common.jpa.User;
import dhbwka.wwi.vertsys.javaee.Arbeitsstundenverwaltung.tasks.ejb.StundeBean;
import dhbwka.wwi.vertsys.javaee.Arbeitsstundenverwaltung.tasks.jpa.Stunde;
import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet für die tabellarische Auflisten der Aufgaben.
 */
@WebServlet(urlPatterns = {"/app/stunden/list/"})
public class StundeListServlet extends HttpServlet {

    
    @EJB
    private UserBean userBean;
    
    @EJB
    private StundeBean stundeBean;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Eigetragene Stunden ermitteln
        User user = this.userBean.getCurrentUser();
        List<Stunde> stunden = this.stundeBean.findByUsername(user.getUsername());
        request.setAttribute("stunden", stunden);
        

        // Anfrage an die JSP weiterleiten
        request.getRequestDispatcher("/WEB-INF/stunden/stunde_list.jsp").forward(request, response);
    }
    
}
