package controllers;

import play.*;
import play.mvc.*;

import play.data.validation.*;

import java.util.*;

import models.Organization;
import models.Citizen;

public class Organizations extends SuperController {

    /**
     GET
     CRUD : Read an organization
     */
    public static void show(Long id) {
        Organization organization = Organization.findById(id);
        render(organization);
    }

    /**
     GET
     CRUD : Create an organization
     */
    public static void form() {
        List<Citizen> cList = Citizen.findAll();
        render(cList);
    }

    /**
     POST
     CRUD : Create an organization
     */
    public static void create(@Required @Valid Organization organization) {
        if(Validation.hasErrors()) {
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
     */
    public static void editForm(Long id) {
        Organization organization = Organization.findById(id);
        render(organization);
    }

    /**
     * POST
     * CRUD : Edit an organization
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
