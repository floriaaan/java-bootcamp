package controllers;

import play.*;
import play.mvc.*;

import play.data.validation.*;

import java.util.*;

import models.Citizen;

public class Citizens extends Controller {

    /**
    GET
    CRUD : Read a citizen
     */
    public static void show(Long id) {
        Citizen c = Citizen.findById(id);
        render(c);
    }
    
    /**
    GET
    CRUD : Create a citizen
     */
    public static void form() {
        render();
    }

    /**
    POST
    CRUD : Create a citizen
     */
    public static void create(@Required @Valid Citizen citizen) {
        if(Validation.hasErrors()) {
            flash.error("Erreur de validation.");
            params.flash();
            Validation.keep();
            form();
        }

        citizen.save();
        show(citizen.id);
    }
}