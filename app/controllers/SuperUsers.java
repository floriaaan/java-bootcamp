package controllers;

import play.*;
import play.mvc.*;

import play.data.validation.*;

import java.util.*;

import models.SuperHero;
import models.SuperVillain;
import models.Citizen;
import models.Organization;
import middlewares.Rights;

/**
 * Super Heroes Controller
 */
public class SuperUsers extends Rights {

    /**
     * GET CRUD : Read all superheroes
     */
    public static void showAll() {
        List<Citizen> citizenList = Citizen.findAll();
        List<Organization> organizationList = Organization.findAll();
        List<SuperHero> superHeroList = SuperHero.findAll();
        List<SuperVillain> superVillainList = SuperVillain.findAll();
        List<SuperHero> validHeroList = SuperHero.find("is_validate", false).fetch();
        List<Citizen> validCitizenList = Citizen.find("waiting_validation", true).fetch();

        render(citizenList, organizationList, superHeroList, superVillainList, validHeroList, validCitizenList);
    }

    /**
     * @param {Long}    id
     * @param {Boolean} bool
     * @param {Boolean} type
     */
    public static void validRole(Long id, Boolean bool, Boolean type) {
        if (type == true) {
            SuperHero superHero = SuperHero.findById(id);
            if (bool == true) {
                superHero.is_validate = true;
                superHero.save();
            } else {
                superHero.delete();
            }
        } else {
            Citizen citizen = Citizen.findById(id);
            citizen.waiting_validation = false;

            if (bool == true) {
                citizen.is_authority = true;
            }

            citizen.save();
        }

        showAll();
    }
}
