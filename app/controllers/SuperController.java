package controllers;

import models.Citizen;
import models.Notification;
import models.SuperHero;
import play.data.validation.Validation;
import play.mvc.Before;
import play.mvc.Controller;

/**
 * Super Controller that manage instance
 */
public class SuperController extends Controller {

    /**
     * Invoke play's controller constructor
     */
    public SuperController() {
        super();
    }

    /**
     * Singleton property
     */
    private static Citizen INSTANCE;

    /**
     * Singleton getter
     * @return {Citizen} INSTANCE
     */
    public static Citizen getAuth() {
        return INSTANCE;
    }

    /**
     * Singleton setter
     * @param {Citizen} c
     */
    public static void setAuth(Citizen c) {
        INSTANCE = c;
    }

    /**
     * Middleware authentication & global variables
     */
    @Before(unless = {"Authentication.login", "Authentication.connect", "Authentication.register", "Authentication.logout", "Citizens.create", "Application.easter"})
    public void middleware_auth() {

        if (this.INSTANCE == null) {
            //System.out.println("unauth");
            flash.error("Please log in.");
            params.flash();
            Validation.keep();
            renderTemplate("Authentication/login.html");
        } else {
            long userSuperheroIdentity = SuperHero.count("identity = ?1", this.INSTANCE);
            long notifications = Notification.count("citizen = ?1 AND is_viewed = 0", this.INSTANCE);
            long isUserSuperhero = SuperHero.count("identity = ?1 AND is_validate = true", this.INSTANCE);


            renderArgs.put("user", this.INSTANCE);
            renderArgs.put("superhero", userSuperheroIdentity);
            renderArgs.put("notifications", notifications);
            renderArgs.put("validatesuperhero", isUserSuperhero);
        }
    }
}
