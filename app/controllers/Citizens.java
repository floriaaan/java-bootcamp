package controllers;

import play.*;
import play.mvc.*;

import play.data.validation.*;

import java.util.*;

import models.*;
import middlewares.Rights;

import lib.BCrypt;

/**
 * Citizen Controller
 */
public class Citizens extends Rights {

    /**
     * GET
     * CRUD : Read all citizens
     */
    public static void showAll() {
        List<Citizen> citizenList = Citizen.findAll();
        render(citizenList);
    }

    /**
     * GET
     * CRUD : Read a citizen
     * @param {Long} id
     */
    public static void show(Long id) {
        Citizen citizen = Citizen.findById(id);

        Citizen auth = getAuth();
        SuperHero superhero = citizen.getSuperHero();
        List<Incident> incidents = null;
        List<Mission> missions = null;
        List<Satisfaction> satisfactions = Satisfaction.find("citizen.id", id).fetch();


        if (auth != null && id == auth.id) {
            incidents = Incident.find("citizen.id", id).fetch();
            if (superhero != null) {
                missions = superhero.getRelatedMissions();
            }
        }

        render(citizen, superhero, incidents, missions, satisfactions);
    }

    /**
     * GET
     * CRUD : Create a citizen
     */
    public static void form() {
        render();
    }

    /**
     * POST
     * CRUD : Create a citizen
     * @param {Citizen} citizen
     */
    public void create(@Required @Valid Citizen citizen) {
        if (Validation.hasErrors()) {
            params.flash();
            Validation.keep();
            form();
        }

        List<Crisis> cList = Crisis.findAll();
        for (int i = 0; i < cList.size(); i++) {

            Crisis crisis = cList.get(i);

            Notification notif = new Notification();
            notif.title = "Crisis notification";
            notif.comments = crisis.comments;
            notif.citizen = citizen;

            citizen.notification_list.add(notif);
        }
        citizen.save();
        setAuth(citizen);
        show(citizen.id);
    }

    /**
     * GET
     * CRUD : Edit a citizen
     * @param {Long} id
     */
    public static void editForm(Long id) {
        Citizen citizen = Citizen.findById(id);
        render(citizen);
    }

    /**
     * POST
     * CRUD : Edit a citizen
     * @param {Citizen} citizen
     * @param {String}  pwd
     */
    public static void edit(Citizen citizen, String pwd) {
        if (BCrypt.checkpw(pwd, citizen.password)) {
            citizen.save();
            show(citizen.id);
        } else {
            flash.error("Wrong credentials");
            params.flash();
            Validation.keep();
            editForm(citizen.id);
        }
    }

    /**
     * Render Role Request page
     */
    public static void roleRequest() {
        render();
    }

    /**
     * Send the request and a notification to the super user
     * @param {Notification} notification
     */
    public static void roleRequested(@Required @Valid Notification notification) {
        Citizen citizen = getAuth();
        Citizen currentCitizen = Citizen.findById(citizen.id);
        currentCitizen.waiting_validation = true;
        currentCitizen.save();

        List<Citizen> cList = Citizen.find("is_superuser", true).fetch();
        Citizen superuser = cList.get(0);
        notification.citizen = superuser;
        superuser.notification_list.add(notification);
        superuser.save();

        show(getAuth().id);
    }

}
