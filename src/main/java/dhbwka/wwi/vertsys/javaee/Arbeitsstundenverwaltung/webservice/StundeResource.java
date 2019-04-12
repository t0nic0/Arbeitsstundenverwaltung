/*
 * Copyright © 2019 Dennis Schulmeister-Zimolong
 * 
 * E-Mail: dhbw@windows3.de
 * Webseite: https://www.wpvs.de/
 * 
 * Dieser Quellcode ist lizenziert unter einer
 * Creative Commons Namensnennung 4.0 International Lizenz.
 */
package dhbwka.wwi.vertsys.javaee.Arbeitsstundenverwaltung.webservice;

import dhbwka.wwi.vertsys.javaee.Arbeitsstundenverwaltung.common.ejb.UserBean;
import dhbwka.wwi.vertsys.javaee.Arbeitsstundenverwaltung.common.jpa.User;
import java.util.List;
import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import dhbwka.wwi.vertsys.javaee.Arbeitsstundenverwaltung.tasks.ejb.StundeBean;
import dhbwka.wwi.vertsys.javaee.Arbeitsstundenverwaltung.tasks.jpa.Stunde;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;

/**
 *
 * @author tonic
 */
@Path("Stunden")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StundeResource {
    
    @EJB
    private StundeBean sBean;
    
     @GET
     @Path("{stundeName}")
    public List<Stunde> findStunde(@PathParam("courseName") String stundeName) {
        Stunde stunde = (Stunde) this.sBean.findByUsername(stundeName);
        List<Stunde> stunden = this.sBean.findByUsername(stundeName);
        return stunden ;
    }
}
