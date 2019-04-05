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

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author tonic
 */
@ApplicationPath("main")
public class AsvRestAPI extends Application {
    @Override
    public Set<Class<?>> getClasses() {
    Set<Class<?>> resources = new HashSet<>();
    // Hier für jede Webservice-Klasse eine Zeile hinzufügen
    resources.add(UserResource.class);

        return resources;
    }
}
