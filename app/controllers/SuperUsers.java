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
        render(citizenList, organizationList, superHeroList, superVillainList);
    }

    public static void validRole(Long id, Boolean bool, Boolean type) {
        if (type == true) {
            SuperHero superHero = SuperHero.findById(id);
            if (bool == true) {
                SuperHero.is_validate = true;
                SuperHero.save();
            } else {
                SuperHero.delete();
            }
        } else {
            Citizen citizen = Citizen.findById(id);
            citizen.waiting_validation = false;

            if (bool == true) {
                citizen.is_authority = true;
            }

            citizen.save();
        }

        showAll()
    }

}
