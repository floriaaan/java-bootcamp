package controllers;

import play.*;
import play.mvc.*;

import play.data.validation.*;

import java.util.*;

import models.Mission;
import models.Incident;
import models.SuperHero;

public class Missions extends Controller {

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
     */
    public static void show(Long id) {
        Mission mission = Mission.findById(id);
        render(mission);
    }

    /**
     * GET
     * CRUD : Create an Mission
     */
    public static void form() {
        List<Incident> iList = Incident.findAll();
        List<SuperHero> sList = SuperHero.findAll();
        render(iList, sList);
    }

    /**
     * POST
     * CRUD : Create an Mission
     */
    public static void create(@Required @Valid Mission mission) {
        if (Validation.hasErrors()) {
            flash.error("Erreur de validation.");
            params.flash();
            Validation.keep();
            form();
        }

        mission.save();
        show(mission.id);
    }
}
