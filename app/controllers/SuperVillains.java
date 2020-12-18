package controllers;

import lib.BCrypt;
import models.Citizen;
import models.SuperVillain;
import play.data.validation.Required;
import play.data.validation.Valid;
import play.data.validation.Validation;

import java.util.List;

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
     * CRUD : Read a super villain
     * @param {Long} id
     */
    public static void show(Long id) {
        SuperVillain s = SuperVillain.findById(id);
        // System.out.println(s.identity);

        render(s);
    }

    /**
     * GET
     * CRUD : Create a super villain
     */
    public static void form() {
        List<Citizen> cList = Citizen.findAll();
        render(cList);
    }

    /**
     * POST
     * CRUD : Create a super villain
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

    /**
     * GET
     * CRUD : Edit a supervillain
     * @param {Long} id
     */
    public static void editForm(Long id) {
        SuperVillain supervillain = SuperVillain.findById(id);
        List<Citizen> cList = Citizen.findAll();

        render(supervillain, cList);
    }

    /**
     * POST
     * CRUD : Edit a supervillain
     * @param {SuperVillain} supervillain
     * @param {String}  pwd
     */
    public static void edit(SuperVillain supervillain, String pwd) {
        Citizen citizen = getAuth();
        if (BCrypt.checkpw(pwd, citizen.password)) {
            supervillain.save();
            show(supervillain.id);
        } else {
            flash.error("Wrong credentials");
            params.flash();
            Validation.keep();
            editForm(supervillain.id);
        }
    }
}
