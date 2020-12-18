package controllers;

import middlewares.Rights;
import models.*;
import play.data.validation.Required;
import play.data.validation.Valid;
import play.data.validation.Validation;

import java.util.List;

/**
 * Crises Controller
 */
public class Crises extends Rights {

    /**
     * GET
     * CRUD : Read all crisis
     * @param {Long} id
     */
    public static void showAll() {
        List<Crisis> crisisList = Crisis.findAll();
        render(crisisList);
    }

    /**
     * GET
     * CRUD : Read a crisis
     * @param {Long} id
     */
    public static void show(Long id) {
        Crisis crisis = Crisis.findById(id);
        render(crisis);
    }

    /**
     * GET
     * CRUD : Create an crisis
     */
    public static void form() {
        List<Mission> mList = Mission.findAll();
        List<Dispute> dList = Dispute.findAll();
        render(mList, dList);
    }

    /**
     * POST
     * CRUD : Create a crisis
     * @param {Crisis} crisis
     */
    public static void create(@Required @Valid Crisis crisis) {
        if (Validation.hasErrors()) {
            flash.error("Erreur de validation.");
            params.flash();
            Validation.keep();
            form();
        }

        List<Citizen> cList = Citizen.findAll();
        for (int i = 0; i < cList.size(); i++) {

            Citizen citizen = cList.get(i);

            Notification notif = new Notification();
            notif.title = "Crisis notification";
            notif.comments = crisis.comments;
            notif.citizen = citizen;
            citizen.notification_list.add(notif);

            citizen.save();
        }

        crisis.save();

        Mission mission = Mission.findById(crisis.mission.id);
        mission.crisis = crisis;
        mission.save();

        show(crisis.id);
    }
}
