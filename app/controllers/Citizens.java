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
        SuperHero superhero = null;
        List<Incident> incidents = null;
        List<Mission> missions = null;


        if (auth != null && id == auth.id) {
            List<SuperHero> query = SuperHero.find("identity.id", id).fetch();
            incidents = Incident.find("citizen.id", id).fetch();
            if (query.size() > 0) {
                superhero = query.get(0);
            }
            if (superhero != null) {
                List<Mission> all = Mission.all().fetch();
                for (Mission m : all) {
                    for (SuperHero s : m.super_heroes_list) {
                        if (s.identity.id == id) {
                            //missions.add(m);
                        }
                    }
                }
            }
        }

        render(citizen, superhero, incidents, missions);
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
     * GET
     */
    public static void roleValidation() {
        List<Citizen> cList = Citizen.findAll();
        render(cList);
    }

    /**
     *
     */
    public static void roleRequest() {
        render();
    }

    /**
     *
     */
    public static void roleRequested(@Required @Valid Notification notification) {

        List<Citizen> cList = Citizen.find("is_superuser", true).fetch();
        Citizen superuser = cList.get(0);
        notification.citizen = superuser;
        superuser.notification_list.add(notification);
        superuser.save();

        show(getAuth().id);
    }

}
