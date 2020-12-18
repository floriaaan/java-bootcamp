package controllers;

import middlewares.Rights;
import models.Citizen;
import models.Crisis;
import models.Dispute;
import models.Mission;
import play.data.validation.Required;
import play.data.validation.Valid;
import play.data.validation.Validation;

import java.util.List;

/**
 * Disputes Controller
 */
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
     * @param {Long} id
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
        List<Mission> missions = Mission.findAll();
        List<Crisis> crises = Crisis.findAll();
        render(cList, missions, crises);
    }

    /**
     * POST
     * CRUD : Create an incident
     * @param {Dispute} dispute
     */
    public static void create(@Required @Valid Dispute dispute) {
        if (Validation.hasErrors()) {
            flash.error("Erreur de validation.");
            params.flash();
            Validation.keep();
            form();
        }

        dispute.save();
        Crisis crisis = Crisis.findById(dispute.crisis.id);
        crisis.dispute = dispute;
        System.out.println();
        crisis.save();
        show(dispute.id);
    }

//    /**
//     * GET
//     * CRUD : Edit an incident
//     * @param {Long} id
//     */
//    public static void editForm(Long id) {
//        Dispute dispute = Dispute.findById(id);
//        render(dispute);
//    }
//
//    /**
//     * POST
//     * CRUD : Edit an incident
//     * @param {Dispute} dispute
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
//    /**
//     * GET
//     * CRUD : Delete an incident
//     * @param {Long} id
//     */
//    public static void delete(Long id) {
//        Dispute dispute = Dispute.findById(id);
//        dispute.delete();
//        showAll();
//    }
}
