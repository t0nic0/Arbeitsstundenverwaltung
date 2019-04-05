/*
 * Copyright © 2019 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.Arbeitsstundenverwaltung.tasks.ejb;

import dhbwka.wwi.vertsys.javaee.Arbeitsstundenverwaltung.common.ejb.UserBean;
import dhbwka.wwi.vertsys.javaee.Arbeitsstundenverwaltung.common.jpa.User;
import dhbwka.wwi.vertsys.javaee.Arbeitsstundenverwaltung.dashboard.ejb.DashboardContentProvider;
import dhbwka.wwi.vertsys.javaee.Arbeitsstundenverwaltung.dashboard.ejb.DashboardSection;
import dhbwka.wwi.vertsys.javaee.Arbeitsstundenverwaltung.dashboard.ejb.DashboardTile;
import dhbwka.wwi.vertsys.javaee.Arbeitsstundenverwaltung.tasks.jpa.Stunde;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * EJB zur Definition der Dashboard-Kacheln für Aufgaben.
 */
@Stateless(name = "stunden")
public class DashboardContent implements DashboardContentProvider {

    /**
     * Vom Dashboard aufgerufenen Methode, um die anzuzeigenden Rubriken und
     * Kacheln zu ermitteln.
     *
     * @param sections Liste der Dashboard-Rubriken, an die die neuen Rubriken
     * angehängt werden müssen
     */
    
    @EJB
    StundeBean stundeBean;
    
    @EJB
    UserBean userBean;
    
    @Override
    public void createDashboardContent(List<DashboardSection> sections) {
        DashboardSection section = new DashboardSection();
        sections.add(section);
        
        DashboardTile tileMonthly = new DashboardTile();
        tileMonthly.setLabel("Monatlich");
        LocalDateTime now = LocalDateTime.now();
        User user = this.userBean.getCurrentUser();
        List<Stunde> tasks = this.stundeBean.findByUsername(user.getUsername());
        int summe = 0;
        for(Stunde i : tasks){
         Date date = i.getDueDate();
         Time time1 = i.getDueTime1();
         Time time2 = i.getDueTime2();
         long diff = time2.getTime() - time1.getTime();
         float diffHour = Math.floorDiv(diff, 60*60*1000);
         if (now.getMonthValue() == date.getMonth() + 1) {
             summe += diffHour;
              }
         }
        tileMonthly.setAmount(summe);
        section.getTiles().add(tileMonthly);
        
        DashboardTile tileWeekly = new DashboardTile();
        tileWeekly.setLabel("Wöchentlich");
        tileWeekly.setAmount(854656);
        section.getTiles().add(tileWeekly);
    }
}
