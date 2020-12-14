package controllers;

import play.*;
import play.mvc.*;

import play.data.validation.*;

import java.util.*;

import models.*;

public class Citizen extends Controller {

    /**
    GET
    CRUD : Read a citizen
     */
    public static void show(Integer id) {
        render(id);
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

        //citizen.save();
        //show(citizen.id);
    }
}