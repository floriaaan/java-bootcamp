package controllers;

import models.Citizen;
import models.Mission;
import models.MissionReport;
import models.Incident;
import models.SuperVillain;
import models.SuperHero;

import play.*;
import play.data.validation.*;
import play.mvc.*;
import java.util.*;

import java.util.*;

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
     */
    public static void show(Long id) {
        MissionReport missionReport = MissionReport.findById(id);
        render(missionReport);
    }

    /**
     * GET
     * CRUD : Create an MissionReport
     */
    public static void form(Long id) {
        Mission mission = Mission.findById(id);
        mission.state = "ended";
        List<SuperHero> super_heroes_list = mission.super_heroes_list;
        for (int i=0; i < super_heroes_list.size(); i++) {
            if (mission.gravity_level == "high") {
                super_heroes_list.get(i).score += 10;
            }
            else if (mission.gravity_level == "moderate") {
                super_heroes_list.get(i).score += 5;
            }
            else if (mission.gravity_level == "low") {
                super_heroes_list.get(i).score += 2;
            }
        }
        mission.save();
        List<SuperVillain> superVillainList = SuperVillain.findAll();
        List<Citizen> CitizenList = Citizen.findAll();
        Incident incident = Incident.findById(mission.getIncidentId());
        Citizen interlocutor = incident.citizen;

        render(mission, superVillainList, CitizenList , interlocutor);
    }

    /**
     * POST
     * CRUD : Create an MissionReport
     */
    public static void create(@Required @Valid MissionReport missionReport) {
        if (Validation.hasErrors()) {
            flash.error("Erreur de validation.");
            params.flash();
            Validation.keep();
            form(missionReport.mission.id);
        }

        missionReport.save();
        show(missionReport.id);
    }
}
