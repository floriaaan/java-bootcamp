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

    @Before(only={"Incidents.showAll"})
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

    @Before(only={"Citizens.showAll", "SuperHeroes.showAll"})
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
