package controllers;

import play.*;
import play.mvc.*;

import play.data.validation.*;

import java.util.*;

import models.Mission;
import models.Incident;
import models.Citizen;
import models.SuperHero;
import models.Notification;

import middlewares.Rights;

/**
 * Missions Controller
 */
public class Missions extends Rights {

    /**
     * GET
     * CRUD : Read all Missions
     */
    public static void showAll() {
        List<Mission> missionList = Mission.findAll();
        render(missionList);
    }

    /**
     * GET
     * CRUD : Read an Mission
     * @param {Long} id
     */
    public static void show(Long id) {
        Mission mission = Mission.findById(id);
        render(mission);
    }

    /**
     * GET
     * CRUD : Create an Mission
     * @param {Long} id
     */
    public static void form(Long id) {
        List<SuperHero> sList = SuperHero.findAll();
        Incident incident = Incident.findById(id);
        render(sList, incident);
    }

    /**
     * POST
     * CRUD : Create an Mission
     * @param {Mission} mission
     */
    public static void create(@Required @Valid Mission mission) {
        if (Validation.hasErrors()) {
            flash.error("Erreur de validation.");
            params.flash();
            Validation.keep();
            form(mission.incident.id);
        }

        Incident incident = Incident.findById(mission.incident.id);
        incident.state = "ended";
        incident.save();

        if (mission.gravity_level == 4) {
            System.out.println("URGENT");

            List<SuperHero> sList = mission.super_heroes_list;

            for (int i = 0; i < sList.size(); i++) {
                SuperHero hero = sList.get(i);

                Notification notif = new Notification();
                notif.title = "Mission notification";
                notif.comments = mission.informations;
                notif.citizen = hero.identity;
                Citizen citizen = hero.identity;
                hero.identity.notification_list.add(notif);

                citizen.save();
            }
        }

        mission.save();
        show(mission.id);
    }
}
