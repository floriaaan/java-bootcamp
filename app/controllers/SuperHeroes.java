package controllers;

import play.*;
import play.mvc.*;

import play.data.validation.*;

import java.util.*;

import models.SuperHero;
import models.Citizen;

public class SuperHeroes extends Controller {

    /**
     * GET CRUD : Read a superhero
     */
    public static void show(Long id) {
        SuperHero s = SuperHero.findById(id);
        render(s);
    }

    /**
     * GET CRUD : Create a superhero
     */
    public static void form() {
        List<Citizen> cList = Citizen.findAll();
        render(cList);
    }

    /**
     * POST CRUD : Create a superhero
     */
    public static void create(@Required @Valid SuperHero superhero) {
        if (Validation.hasErrors()) {
            params.flash();
            Validation.keep();
            form();
        }

        superhero.save();
        show(superhero.id);
    }
}