package controllers;

import play.*;
import play.mvc.*;

import play.data.validation.*;

import java.util.*;

import models.SuperHero;
import models.SuperVillain;
import models.Citizen;
import models.Organization;
import models.Notification;
import middlewares.Rights;

/**
 * Super Heroes Controller
 */
public class SuperUsers extends Rights {

    /**
     * GET
     * CRUD : Read all citizens, organizations, superheroes, supervillains / Display role requests
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
     * GET
     * CRUD : Manange the role validations
     * @param {Long}    id
     * @param {Boolean} bool
     * @param {Boolean} type
     */
    public static void validRole(Long id, Boolean bool, Boolean type) {
        Notification notif = new Notification();
        if (type == true) {
            SuperHero superHero = SuperHero.findById(id);
            if (bool == true) {
                superHero.is_validate = true;
                superHero.save();
                notif.comments = "Your request has been accepted";
            } else {
                superHero.delete();
                notif.comments = "your request was not accepted";
            }
            notif.title = "Information about your super hero request";
            notif.citizen = superHero.identity;
            superHero.identity.notification_list.add(notif);

            superHero.identity.save();

        } else {
            Citizen citizen = Citizen.findById(id);
            citizen.waiting_validation = false;

            if (bool == true) {
                citizen.is_authority = true;
                notif.comments = "Your request has been accepted";
            }else{
                notif.comments = "your request was not accepted";
            }
            notif.title = "Information about your role request";
            notif.citizen = citizen;
            citizen.notification_list.add(notif);

            citizen.save();
        }

        if (bool == true) {
            notif.comments = "Your request has been accepted";
        } else {
            notif.comments = "your request was not accepted";
        }


        showAll();
    }

    /**
     * POST
     * CRUD : Declare a citizen's death
     * @param {Date}  dead_date
     * @param {Long}  citizen_id
     */
    public static void declareDead(@Required Date dead_date, @Required Long citizen_id) {
        Citizen citizen = Citizen.findById(citizen_id);
        citizen.deathdate = dead_date;
        citizen.save();

        Citizens.showAll();
    };

    /**
     * POST
     * CRUD : Declare a citizen alive
     * @param {Long}  citizen_id
     */
    public static void declareAlive(@Required Long citizen_id) {
        Citizen citizen = Citizen.findById(citizen_id);
        citizen.deathdate = null;
        citizen.save();

        Citizens.showAll();
    };
}
