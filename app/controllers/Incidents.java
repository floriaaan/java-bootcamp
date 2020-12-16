package controllers;

import play.*;
import play.mvc.*;

import play.data.validation.*;

import java.util.*;
import java.io.File;

import play.mvc.Http;

import models.*;
import middlewares.Rights;

public class Incidents extends Rights {

    public static void showAll() {
        List<Incident> incidentsList = Incident.findAll();
        render(incidentsList);
    }

    public static void setAside(Long id) {
        Incident incident = Incident.findById(id);
        incident.state = "aside";
        incident.save();
        showAll();
    }

    /**
     * GET
     * CRUD : Read an incident
     */
    public static void show(Long id) {
        Incident incident = Incident.findById(id);
        Mission mission = incident.getMission();
        render(incident, mission);
    }

    /**
     * GET
     * CRUD : Create an incident
     */
    public static void form() {
        Long incidentsNb = Incident.count();
        Citizen auth = getAuth();

        Organization organization = null;
        if (auth != null) {
            organization = auth.getOrg();
        }
            

        render(incidentsNb, organization);
    }

    /**
     * POST
     * CRUD : Create an incident
     */
    public static void create(@Required @Valid Incident incident, @Required String reporter_type) {
        System.out.println(reporter_type);
        if (Validation.hasErrors()) {
            flash.error("Erreur de validation.");
            params.flash();
            Validation.keep();
            form();
        }

        if (reporter_type.equals("organization")) {
            incident.is_organization = true;
        } else {
            incident.is_organization = false;
        }

        incident.save();
        show(incident.id);
    }

    /**
     * GET
     * CRUD : Edit an incident
     */
    public static void editForm(Long id) {
        Incident incident = Incident.findById(id);
        render(incident);
    }

    /**
     * POST
     * CRUD : Edit an incident
     */
    public static void edit(@Required @Valid Incident incident) {
        if (Validation.hasErrors()) {
            params.flash();
            Validation.keep();
            form();
        }

        incident.save();
        show(incident.id);
    }

    public static void delete(Long id) {
        Incident incident = Incident.findById(id);
        incident.delete();
        showAll();
    }
}
