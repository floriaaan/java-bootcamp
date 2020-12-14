package controllers;

import play.*;
import play.mvc.*;

import play.data.validation.*;

import java.util.*;

import models.Incident;

public class Incidents extends Controller {

    /**
     GET
     CRUD : Read an incident
     */
    public static void show(Long id) {
        Incident incident = Incident.findById(id);
        render(incident);
    }

    /**
     GET
     CRUD : Create an incident
     */
    public static void form() {
        render();
    }

    /**
     POST
     CRUD : Create an incident
     */
    public static void create(@Required @Valid Incident incident) {
        if(Validation.hasErrors()) {
            flash.error("Erreur de validation.");
            params.flash();
            Validation.keep();
            form();
        }

        incident.save();
        show(incident.id);
    }
}
