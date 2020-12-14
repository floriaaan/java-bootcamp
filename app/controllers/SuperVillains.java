package controllers;

import play.*;
import play.mvc.*;

import play.data.validation.*;

import java.util.*;

import models.SuperVillain;
import models.Citizen;

public class SuperVillains extends Controller {

    /**
     * GET
     * CRUD : Read a super villains
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