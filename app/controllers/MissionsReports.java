package controllers;

import models.Citizen;
import models.Mission;
import models.MissionReport;
import models.Incident;
import models.SuperVillain;
import models.SuperHero;
import models.Crisis;

import play.*;
import play.data.validation.*;
import play.mvc.*;

import java.util.*;

import java.util.*;

/**
 * Missions reports Controller
 */
public class MissionsReports extends SuperController {

    /**
     * GET
     * CRUD : Read all Mission reports
     */
    public static void showAll() {
        List<MissionReport> missionReportList = MissionReport.findAll();
        render(missionReportList);
    }

    /**
     * GET
     * CRUD : Read an MissionReport
     * @param {Long} id
     */
    public static void show(Long id) {
        MissionReport missionReport = MissionReport.findById(id);
        Long crisisId = missionReport.mission.getCrisisId();
        render(missionReport , crisisId);
    }

    /**
     * GET
     * CRUD : Create an MissionReport
     * @param {Long} id
     */
    public static void form(Long id) {
        Mission mission = Mission.findById(id);
        mission.state = "ended";
        List<SuperHero> super_heroes_list = mission.super_heroes_list;
        for (int i = 0; i < super_heroes_list.size(); i++) {
            SuperHero hero = SuperHero.findById(super_heroes_list.get(i).id);
            if (mission.gravity_level == 4) {
                System.out.println("URGENT");
                hero.score += 15;
            } else if (mission.gravity_level == 3) {
                System.out.println("HIGH");
                hero.score += 10;
            } else if (mission.gravity_level == 2) {
                System.out.println("MODERATE");
                hero.score += 5;
            } else if (mission.gravity_level == 1) {
                System.out.println("LOW");
                hero.score += 2;
            }
            hero.save();
        }
        mission.save();
        List<SuperVillain> superVillainList = SuperVillain.findAll();
        List<Citizen> CitizenList = Citizen.findAll();
        Incident incident = Incident.findById(mission.getIncidentId());
        Citizen interlocutor = incident.citizen;

        render(mission, superVillainList, CitizenList, interlocutor);
    }

    /**
     * POST
     * CRUD : Create an MissionReport
     * @param {MissionReport} missionReport
     */
    public static void create(@Required @Valid MissionReport missionReport) {
        if (Validation.hasErrors()) {
            flash.error("Erreur de validation.");
            params.flash();
            Validation.keep();
            form(missionReport.mission.id);
        }

        List<SuperVillain> sList = missionReport.superVillainList;
        Mission mission = missionReport.mission;
        for (int i = 0; i < sList.size(); i++) {

            SuperVillain villain = SuperVillain.findById(sList.get(i).id);
            if (mission.gravity_level == 4) {
                System.out.println("URGENT");
                villain.malicious_score += 15;
            } else if (mission.gravity_level == 3) {
                System.out.println("HIGH");
                villain.malicious_score += 10;
            } else if (mission.gravity_level == 2) {
                System.out.println("MODERATE");
                villain.malicious_score += 5;
            } else if (mission.gravity_level == 1) {
                System.out.println("LOW");
                villain.malicious_score += 2;
            }
            villain.save();
        }

        missionReport.save();
        show(missionReport.id);
    }
}
