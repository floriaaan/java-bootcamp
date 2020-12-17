package controllers;

import play.*;
import play.mvc.*;

import play.data.validation.*;

import java.util.*;
import java.io.*;

import play.mvc.Http;

import models.*;
import middlewares.Rights;

import play.data.Upload;

/**
 * Incidents Controller
 */
public class Incidents extends Rights {
    /**
     * GET
     * CRUD : Read all incidents
     */
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
     * @param {Long} id
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
     * @param {Incident} incident
     * @param {String}   reporter_type
     */
    public static void create(@Required @Valid Incident incident, @Required String reporter_type, Upload picture) {
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

        try {
            File file = picture.asFile();

            byte[] data = new byte[(int) file.length()];
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(data);
            fileInputStream.close();

            incident.pictureData = data;
            incident.pictureMime = picture.getContentType();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        

        incident.save();
        show(incident.id);
    }

    /**
     * GET
     * CRUD : Edit an incident
     * @param {Long} id
     */
    public static void editForm(Long id) {
        Incident incident = Incident.findById(id);
        render(incident);
    }

    /**
     * POST
     * CRUD : Edit an incident
     * @param {Incident} incident
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

    /**
     * GET
     * CRUD : Delete an incident
     * @param {Long} id
     */
    public static void delete(Long id) {
        Incident incident = Incident.findById(id);
        incident.delete();
        showAll();
    }

    public static void getFile(Long id) {
        Incident incident = Incident.findById(id);
        if (incident.pictureData != null) {
            try{
                ByteArrayInputStream inputstream = new ByteArrayInputStream(incident.pictureData);
                response.setContentTypeIfNotSet(incident.pictureMime);
                renderBinary(inputstream);
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
            
        }
    }
}
