/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.Arbeitsstundenverwaltung.tasks.ejb;

import dhbwka.wwi.vertsys.javaee.Arbeitsstundenverwaltung.common.ejb.EntityBean;
import dhbwka.wwi.vertsys.javaee.Arbeitsstundenverwaltung.tasks.jpa.Task;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
/**
 * Einfache EJB mit den üblichen CRUD-Methoden für Aufgaben
 */
@Stateless
@RolesAllowed("app-user")
public class TaskBean extends EntityBean<Task, Long> { 
   
    public TaskBean() {
        super(Task.class);
    }
    
    /**
     * Alle Aufgaben eines Benutzers, nach Fälligkeit sortiert zurückliefern.
     * @param username Benutzername
     * @return Alle Aufgaben des Benutzers
     */
    public List<Task> findByUsername(String username) {
        return em.createQuery("SELECT t FROM Task t WHERE t.owner.username = :username ORDER BY t.dueDate, t.dueTime1, t.dueTime2")
                 .setParameter("username", username)
                 .getResultList();
    }
}
