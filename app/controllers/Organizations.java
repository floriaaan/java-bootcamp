package controllers;

import play.*;
import play.mvc.*;

import play.data.validation.*;

import java.util.*;

import models.Organization;

public class Organizations extends Controller {

    /**
     GET
     CRUD : Read an incident
     */
    public static void show(Long id) {
        Organization organization = Organization.findById(id);
        render(organization);
    }

    /**
     GET
     CRUD : Create an incident
     */
    public static void form() {
        render();
    }

    /**
     POST
     CRUD : Create an incident
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
}
