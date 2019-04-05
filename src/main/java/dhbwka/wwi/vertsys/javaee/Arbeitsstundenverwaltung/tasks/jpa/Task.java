/*
 * Copyright © 2018 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.Arbeitsstundenverwaltung.tasks.jpa;

import dhbwka.wwi.vertsys.javaee.Arbeitsstundenverwaltung.common.jpa.User;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;

/**
 * Eine zu erledigende Aufgabe.
 */
@Entity
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "task_ids")
    @TableGenerator(name = "task_ids", initialValue = 0, allocationSize = 50)
    private long id;

    @ManyToOne
    @NotNull(message = "Die Aufgabe muss einem Benutzer geordnet werden.")
    private User owner;
   
    @NotNull(message = "Das Datum darf nicht leer sein.")
    private Date dueDate;

    @NotNull(message = "Die Uhrzeit darf nicht leer sein.")
    private Time dueTime1;
    
    @NotNull(message = "Die Uhrzeit darf nicht leer sein.")
    private Time dueTime2;

   

    //<editor-fold defaultstate="collapsed" desc="Konstruktoren">
    public Task() {
    }

    public Task(long id, User owner, Date dueDate, Time dueTime1, Time dueTime2) {
        this.id = id;
        this.owner = owner;
        this.dueDate = dueDate;
        this.dueTime1 = dueTime1;
        this.dueTime2 = dueTime2;
    }
    

    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setter und Getter">
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Time getDueTime1() {
        return dueTime1;
    }

    public void setDueTime1(Time dueTime1) {
        this.dueTime1 = dueTime1;
    }
    
     public Time getDueTime2() {
        return dueTime2;
    }
     public void setDueTime2(Time dueTime2) {
        this.dueTime2 = dueTime2;
    }

   
    //</editor-fold>

}
