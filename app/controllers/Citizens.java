package controllers;

import play.*;
import play.mvc.*;

import play.data.validation.*;

import java.util.*;

import models.Citizen;
import middlewares.Rights;

public class Citizens extends Rights {

    /**
     GET
     CRUD : Read all citizens
     */
    public static void showAll() {
        List<Citizen> citizenList = Citizen.findAll();
        render(citizenList);
    }

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
    public void create(@Required @Valid Citizen citizen) {
        if(Validation.hasErrors()) {
            params.flash();
            Validation.keep();
            form();
        }

        citizen.save();
        setAuth(citizen);
        show(citizen.id);
    }

    /**
    GET
    CRUD : Edit a citizen
     */
    public static void editForm(Long id) {
        Citizen c = Citizen.findById(id);
        render(c);
    }

    /**
    POST
    CRUD : Edit a citizen
     */
    public static void edit(@Required @Valid Citizen citizen) {
        if(Validation.hasErrors()) {
            params.flash();
            Validation.keep();
            form();
        }

        citizen.save();
        show(citizen.id);
    }
}
