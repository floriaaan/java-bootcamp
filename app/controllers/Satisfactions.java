package controllers;

import models.Mission;
import models.Satisfaction;
import play.data.validation.Required;
import play.data.validation.Valid;
import play.data.validation.Validation;

import java.util.List;

/**
 * Satisfactions Controller
 */
public class Satisfactions extends SuperController {

    /**
     * GET
     * CRUD : Read all satisfactions
     */
    public static void showAll() {
        List<Satisfaction> satisfactionsList = Satisfaction.findAll();
        render(satisfactionsList);
    }

    /**
     * GET
     * CRUD : Read a satisfaction
     * @param {Long} id
     */
    public static void show(Long id) {
        Satisfaction satisfaction = Satisfaction.findById(id);
        render(satisfaction);
    }

    /**
     * GET
     * CRUD : Create a satifaction
     * @param {Long} id
     */
    public static void form(Long id) {
        Mission mission = Mission.findById(id);
        render(mission);
    }

    /**
     * POST
     * CRUD : Create a satisfaction
     * @param {Satisfaction} satisfaction
     */
    public static void create(@Required @Valid Satisfaction satisfaction) {
        if (Validation.hasErrors()) {
            flash.error("Erreur de validation.");
            params.flash();
            Validation.keep();
            form(satisfaction.mission.id);
        }

        satisfaction.save();
        show(satisfaction.id);
    }
}
