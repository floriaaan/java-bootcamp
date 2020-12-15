package controllers;

import play.*;
import play.mvc.*;

import play.data.validation.*;

import java.util.*;

import models.Incident;
import models.Citizen;
import middlewares.Rights;

public class Incidents extends Rights {

    public static void showAll() {
        List<Incident> incidentsList = Incident.findAll();
        render(incidentsList);
    }

    /**
     GET
     CRUD : Read an incident
     */
    public static void show(Long id) {
        Incident incident = Incident.findById(id);
        // TODO : render mission as mission
        render(incident);
    }

    /**
     GET
     CRUD : Create an incident
     */
    public static void form() {
        List<Citizen> cList = Citizen.findAll();
        render(cList);
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
