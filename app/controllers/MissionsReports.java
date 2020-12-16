package controllers;

import models.Citizen;
import models.Mission;
import models.MissionReport;
import models.Incident;
import models.SuperVillain;

import play.*;
import play.data.validation.*;
import play.mvc.*;
import java.util.*;

import java.util.*;

public class MissionsReports extends SuperController {

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
