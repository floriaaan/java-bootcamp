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
        System.out.println(organization.boss);
        if(Validation.hasErrors()) {
            flash.error("Erreur de validation.");
            params.flash();
            Validation.keep();
            form();
        }

        organization.save();
        show(organization.id);
    }
}
