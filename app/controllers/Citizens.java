package controllers;

import play.*;
import play.mvc.*;

import play.data.validation.*;

import java.util.*;

import models.Citizen;
import models.SuperHero;
import middlewares.Rights;

import lib.BCrypt;


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
        Citizen citizen = Citizen.findById(id);

        SuperHero superhero = null;
        Citizen auth = getAuth();

        if (auth != null && id == auth.id) {
            List<SuperHero> query = SuperHero.find("identity.id", id).fetch();
            superhero = query.get(0);
        }

        render(citizen, superhero);
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
        Citizen citizen = Citizen.findById(id);
        render(citizen);
    }

    /**
    POST
    CRUD : Edit a citizen
     */
    public static void edit(Citizen citizen, String pwd) {
        if(BCrypt.checkpw(pwd, citizen.password)) {
            citizen.save();
            show(citizen.id);
        } else {
            flash.error("Wrong credentials");
            params.flash();
            Validation.keep();
            editForm(citizen.id);
        }
    }
}
