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
public class Superusers extends Rights {

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

}
