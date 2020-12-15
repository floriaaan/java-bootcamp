package controllers;

import play.*;
import play.mvc.*;

import play.data.validation.*;

import java.util.*;

import models.Mission;
import models.Incident;
import models.SuperHero;

public class Missions extends SuperController {

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
    public static void form(Long id) {
        List<SuperHero> sList = SuperHero.findAll();
        Incident incident = Incident.findById(id);
        render(sList, incident);
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
            form(mission.incident.id);
        }

        mission.save();
        show(mission.id);
    }
}
