package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Scope.*;

import java.util.*;
import play.data.validation.*;

import models.*;


public class SuperController extends Controller {

    public SuperController()
    {
        super();
    }

    private static Citizen INSTANCE;

    public static Citizen getAuth()
    {
        return INSTANCE;
    }

    public static void setAuth(Citizen c)
    {
        INSTANCE = c;
    }


    @Before(unless={"Authentication.login", "Authentication.connect", "Authentication.register", "Authentication.logout", "Citizens.create"})
    public void middleware_auth() {

        if(this.INSTANCE == null) {
            //System.out.println("unauth");
            flash.error("Please log in.");
            params.flash();
            Validation.keep();
            renderTemplate("Authentication/login.html");
        } else {
            //System.out.println(this.INSTANCE.getName());
            long userSuperheroIdentity = SuperHero.count("identity = ?1", this.INSTANCE);
            long notifications = Notification.count("citizen = ?1 AND is_viewed = 0", this.INSTANCE);


            renderArgs.put("user", this.INSTANCE);
            renderArgs.put("superhero", userSuperheroIdentity);
            renderArgs.put("notifications", notifications);
        }
    }

}
