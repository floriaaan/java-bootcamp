package controllers;

import play.*;
import play.mvc.*;

import play.data.validation.*;

import java.util.*;

import models.Crisis;
import models.Mission;
import models.Dispute;
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

        crisis.save();
        show(crisis.id);
    }

}