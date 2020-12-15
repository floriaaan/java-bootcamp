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
            Application.index();
        }
        if (!citizen.is_authority || !citizen.is_superuser) {
            Application.index();
        }
    }

    @Before(only={"Citizens.showAll", "SuperHeroes.showAll"})
    public static void middleware_superuser() {
        Citizen citizen = getAuth();
        if (citizen == null) {
            Application.index();
        }
        if (!citizen.is_superuser) {
            Application.index();
        }
    }
}
