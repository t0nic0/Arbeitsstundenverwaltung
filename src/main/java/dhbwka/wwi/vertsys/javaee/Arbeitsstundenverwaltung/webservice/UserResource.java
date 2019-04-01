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
import javax.ws.rs.GET;

/**
 *
 * @author tonic
 */
class UserResource {
    @EJB
    private UserBean ub;
     @GET
    public List<User> findUser() {
        return (List<User>) this.ub.getCurrentUser();
    }
}
