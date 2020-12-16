package controllers;

import play.*;
import play.mvc.*;

import play.data.validation.*;

import java.util.*;

import models.SuperVillain;
import models.Citizen;

/**
 * Super Villains Controller
 */
public class SuperVillains extends SuperController {

    /**
     * GET CRUD : Read all villains
     */
    public static void showAll() {
        List<SuperVillain> superVillainsList = SuperVillain.findAll();
        render(superVillainsList);
    }

    /**
     * GET
     * CRUD : Read a super villains
     * @param {Long} id
     */
    public static void show(Long id) {
        SuperVillain s = SuperVillain.findById(id);
        System.out.println(s.identity);

        render(s);
    }

    /**
     * GET
     * CRUD : Create a super villains
     */
    public static void form() {
        List<Citizen> cList = Citizen.findAll();
        render(cList);
    }

    /**
     * POST
     * CRUD : Create a super villains
     * @param {SuperVillain} supervillain
     */
    public static void create(@Required @Valid SuperVillain supervillain) {
        if (Validation.hasErrors()) {
            params.flash();
            Validation.keep();
            form();
        }
        supervillain.save();
        show(supervillain.id);
    }
}