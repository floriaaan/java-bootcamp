package controllers;

import play.*;
import play.mvc.*;

import play.data.validation.*;

import java.util.*;
import java.io.File;

import play.mvc.Http;

import middlewares.Rights;
import models.Dispute;
import models.Citizen;
import models.Mission;

public class Disputes extends Rights {

    /**
     * GET
     * CRUD : Read all disputes
     */
    public static void showAll() {
        List<Dispute> disputeList = Dispute.findAll();
        render(disputeList);
    }

    /**
     * GET
     * CRUD : Read a dispute
     */
    public static void show(Long id) {
        Dispute dispute = Dispute.findById(id);
        render(dispute);
    }

    /**
     * GET
     * CRUD : Create an incident
     */
    public static void form() {
        List<Citizen> cList = Citizen.findAll();
//        Mission mission = Mission.findById(id);
        render(cList);
    }

    /**
     * POST
     * CRUD : Create an incident
     */
    public static void create(@Required @Valid Dispute dispute) {
        if (Validation.hasErrors()) {
            flash.error("Erreur de validation.");
            params.flash();
            Validation.keep();
            form();
        }

        dispute.save();
        show(dispute.id);
    }

//    /**
//     GET
//     CRUD : Edit an incident
//     */
//    public static void editForm(Long id) {
//        Dispute dispute = Dispute.findById(id);
//        render(dispute);
//    }
//
//    /**
//     POST
//     CRUD : Edit an incident
//     */
//    public static void edit(@Required @Valid Dispute dispute) {
//        if(Validation.hasErrors()) {
//            params.flash();
//            Validation.keep();
//            form();
//        }
//
//        dispute.save();
//        show(dispute.id);
//    }
//
//    public static void delete(Long id) {
//        Dispute dispute = Dispute.findById(id);
//        dispute.delete();
//        showAll();
//    }
}
