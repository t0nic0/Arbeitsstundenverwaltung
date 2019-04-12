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
    
        LocalDateTime now = LocalDateTime.now();
        User user = this.userBean.getCurrentUser();
        List<Stunde> tasks = this.stundeBean.findByUsername(user.getUsername());
        
        int summeMounthly = 0;
        int summeYearly = 0;
        for(Stunde i : tasks){
         Date date = i.getDueDate();
         Time time1 = i.getDueTime1();
         Time time2 = i.getDueTime2();
         long diff = time2.getTime() - time1.getTime();
         int year = date.getYear()+1900;
         int thisYear = now.getYear() ;
         int thisMonth = now.getMonthValue();
         int month= date.getMonth() + 1;
         float diffHour = Math.floorDiv(diff, 60*60*1000);
         if (thisYear == year && thisMonth == month) {
             summeMounthly += diffHour;
            }
         if ( thisYear == year){
             summeYearly += diffHour;
            }   
        }
         
        DashboardTile tileYearly = new DashboardTile();
        tileYearly.setLabel("Jährlich");
        tileYearly.setAmount(summeYearly);
        section.getTiles().add(tileYearly);
        
        DashboardTile tileMonthly = new DashboardTile();
        tileMonthly.setLabel("Monatlich");
        tileMonthly.setAmount(summeMounthly);
        section.getTiles().add(tileMonthly);   
    }
}
