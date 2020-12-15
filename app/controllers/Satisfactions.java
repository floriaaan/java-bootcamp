package controllers;

import play.*;
import play.mvc.*;

import play.data.validation.*;

import java.util.*;

import models.Satisfaction;
import models.Mission;
import models.SuperHero;
import models.Citizen;

public class Satisfactions extends SuperController {

    public static void showAll() {
        List<Satisfaction> satisfactionsList = Satisfaction.findAll();
        render(satisfactionsList);
    }

    /**
     GET
     CRUD : Read an incident
     */
    public static void show(Long id) {
        Satisfaction satisfaction = Satisfaction.findById(id);
        render(satisfaction);
    }

    /**
     GET
     CRUD : Create an satifaction form
     */
    public static void form() {
        List<Citizen> cList = Citizen.findAll();
        List<Mission> mList = Mission.findAll();
        render(cList , mList);
    }

    /**
     POST
     CRUD : Create an incident
     */
    public static void create(@Required @Valid Satisfaction satisfaction) {
        if(Validation.hasErrors()) {
            flash.error("Erreur de validation.");
            params.flash();
            Validation.keep();
            form();
        }

        satisfaction.save();
        show(satisfaction.id);
    }
}
