package controllers;

import play.*;
import play.mvc.*;

import play.data.validation.*;

import java.util.*;

import models.Crisis;
import models.Mission;
import models.Dispute;
import models.Citizen;
import models.Notification;
import middlewares.Rights;

import lib.BCrypt;


public class Crises extends Rights {

    /**
     * GET
     * CRUD : Read an crisis
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
     * CRUD : Create an crisis
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
            notif.comments = crisis.comments;
            notif.citizen = citizen;
            citizen.notification_list.add(notif);

            citizen.save();
        }

        crisis.save();
        show(crisis.id);
    }

}