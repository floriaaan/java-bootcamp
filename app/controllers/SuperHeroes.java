package controllers;

import play.*;
import play.mvc.*;

import play.data.validation.*;

import java.util.*;

import models.SuperHero;
import models.Citizen;
import middlewares.Rights;

public class SuperHeroes extends Rights {

    /**
     * GET CRUD : Read all superheroes
     */
    public static void showAll() {
        List<SuperHero> superHeroesList = SuperHero.findAll();
        render(superHeroesList);
    }

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
