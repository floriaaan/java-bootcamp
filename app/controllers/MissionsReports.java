package controllers;

import models.Citizen;
import models.Mission;
import models.MissionReport;
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
        List<SuperVillain> superVillainList = SuperVillain.findAll();
        List<Citizen> CitizenList = Citizen.findAll();

        render(mission, superVillainList, CitizenList);
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
