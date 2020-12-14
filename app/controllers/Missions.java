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
        System.out.println(mission.title);
        System.out.println(mission.type);
        System.out.println(mission.incidentList);
        System.out.println(mission.startedDate);
        System.out.println(mission.enddedDate);
        System.out.println(mission.address);
        System.out.println(mission.informartions);
        System.out.println(mission.superHeroList);
        System.out.println(mission.gravityLevel);
        System.out.println(mission.emergencyLevel);
        System.out.println(mission.created_at);
        System.out.println(mission.updated_at);
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