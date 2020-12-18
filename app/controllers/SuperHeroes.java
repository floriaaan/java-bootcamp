package controllers;

import middlewares.Rights;
import models.Citizen;
import models.Mission;
import models.Notification;
import models.SuperHero;
import play.data.validation.Required;
import play.data.validation.Valid;
import play.data.validation.Validation;

import java.util.List;

/**
 * Super Heroes Controller
 */
public class SuperHeroes extends Rights {

    /**
     * GET CRUD : Read all superheroes
     */
    public static void showAll() {
        List<SuperHero> superHeroesList = SuperHero.findAll();
        render(superHeroesList);
    }

    /**
     * GET
     * CRUD : Read a superhero
     * @param {Long} id
     */
    public static void show(Long id) {
        SuperHero s = SuperHero.findById(id);
        List<Mission> missions = s.getRelatedMissions();
        render(s, missions);
    }

    /**
     * GET
     * CRUD : Create a superhero
     */
    public static void form() {
        List<Citizen> cList = Citizen.findAll();
        render(cList);
    }

    /**
     * POST
     * CRUD : Create a superhero
     * @param {SuperHero} superhero
     */
    public static void create(@Required @Valid SuperHero superhero) {
        if (Validation.hasErrors()) {
            params.flash();
            Validation.keep();
            form();
        }


        List<Citizen> cList = Citizen.find("is_superuser", true).fetch();
        Citizen superuser = cList.get(0);

        Notification notif = new Notification();
        notif.title = "Request to be a Super Hero";
        notif.comments = "Super Hero name : " + superhero.name + "\n" + "power : " + superhero.power + "\n" + "weakness : " + superhero.weakness;
        notif.citizen = superuser;

        superuser.notification_list.add(notif);
        superuser.save();

        superhero.save();
        show(superhero.id);
    }
}
