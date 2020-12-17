package middlewares;

import play.*;
import play.mvc.*;
import play.mvc.Scope.*;

import java.util.*;
import play.data.validation.*;

import models.*;
import controllers.SuperController;
import controllers.Application;


public class Rights extends SuperController {

    @Before(only={"Crises.showAll","Disputes.showAll", "Incidents.showAll", "Missions.create", "MissionReports.showAll", "MissionReports.create", "MissionReports.show", "Satisfactions.showAll", "SuperHeroes.showAll", "SuperVillains.showAll"})
    static void middleware() {
        Citizen citizen = getAuth();
        if (citizen == null) {
            flash.error("You do not have access to this ressource");
            params.flash();
            Validation.keep();
            Application.index();
        }
        if (!citizen.is_authority) {
             flash.error("You do not have access to this ressource");
            params.flash();
            Validation.keep();
            Application.index();
        }
    }

    @Before(only={"SuperUsers.showAll"})
    public static void middleware_superuser() {
        Citizen citizen = getAuth();
        if (citizen == null) {
             flash.error("You do not have access to this ressource");
            params.flash();
            Validation.keep();
            Application.index();
        }
        if (!citizen.is_superuser) {
             flash.error("You do not have access to this ressource");
            params.flash();
            Validation.keep();
            Application.index();
        }
    }
}
