package controllers;

import middlewares.Rights;
import models.*;
import play.data.validation.Required;
import play.data.validation.Valid;
import play.data.validation.Validation;

import java.util.List;

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
     * CRUD : Read a Mission
     * @param {Long} id
     */
    public static void show(Long id) {
        Mission mission = Mission.findById(id);
        render(mission);
    }

    /**
     * GET
     * CRUD : Create a Mission
     * @param {Long} id
     */
    public static void form(Long id) {
        List<SuperHero> sList = SuperHero.findAll();
        Incident incident = Incident.findById(id);
        render(sList, incident);
    }

    /**
     * POST
     * CRUD : Create a Mission
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

    /**
     * POST
     * CRUD : Set mission state to closed
     * @param {Long} id
     */
    public static void closeMission(Long id) {
        Mission mission = Mission.findById(id);
        mission.state = "closed";
        mission.save();
        showAll();
    }

    /**
     * GET
     * CRUD : Set mission state to in progress
     * @param {Long} id
     */
    public static void openMission(Long id) {
        Mission mission = Mission.findById(id);
        mission.state = "in progress";
        mission.save();
        showAll();
    }

}
