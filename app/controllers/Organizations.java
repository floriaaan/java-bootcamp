package controllers;

import lib.BCrypt;
import models.Citizen;
import models.Incident;
import models.Organization;
import models.SuperHero;
import play.data.validation.Required;
import play.data.validation.Valid;
import play.data.validation.Validation;

import java.util.List;

/**
 * Organisations Controller
 */
public class Organizations extends SuperController {

    /**
     * GET
     * CRUD : Read an organization
     * @param {Long} id
     */
    public static void show(Long id) {
        Organization organization = Organization.findById(id);
        long implicated = Incident.count("is_organization = 1 and citizen.id = ?1", organization.boss.id);

        long missions = 0;
        List<Incident> iList = Incident.find("is_organization = 1 and citizen.id = ?1", organization.boss.id).fetch();
        for(Incident i: iList){
            if (i.mission != null) {
                missions++;
            }
        }


        long superheroes = 0;
        for(Citizen c: organization.members){
            if (SuperHero.count("identity.id = ?1", c.id) > 0) {
                superheroes++;
            }
        }

        render(organization, implicated, superheroes, missions);
    }

    /**
     * GET
     * CRUD : Create an organization
     */
    public static void form() {
        List<Citizen> cList = Citizen.findAll();
        render(cList);
    }

    /**
     * POST
     * CRUD : Create an organization
     * @param {Organization} organization
     */
    public static void create(@Required @Valid Organization organization) {
        if (Validation.hasErrors()) {
            flash.error("Erreur de validation.");
            params.flash();
            Validation.keep();
            form();
        }

        organization.save();
        show(organization.id);
    }

    /**
     * GET
     * CRUD : Edit an organization
     * @param {Long} id
     */
    public static void editForm(Long id) {
        Organization organization = Organization.findById(id);
        List<Citizen> cList = Citizen.findAll();
        render(organization , cList);
    }

    /**
     * POST
     * CRUD : Edit an organization
     * @param {Organization} organization
     * @param {String} pwd
     */
    public static void edit(Organization organization, String pwd) {
        if (BCrypt.checkpw(pwd, organization.boss.password)) {
            organization.save();
            show(organization.id);
        } else {
            flash.error("Wrong credentials");
            params.flash();
            Validation.keep();
            editForm(organization.id);
        }
    }
}
