package controllers;

import lib.BCrypt;
import models.Citizen;
import play.data.validation.Required;
import play.data.validation.Validation;

import java.util.List;

/**
 * Authentication Controller
 */
public class Authentication extends SuperController {

    /**
     * login method
     */
    public static void login() {
        render();
    }

    /**
     * Loging method
     * @param {String} mail
     * @param {String} password
     */
    public void connect(@Required String mail, @Required String password) {
        if (Validation.hasErrors()) {
            params.flash();
            Validation.keep();
            login();
        }

        List<Citizen> auth = Citizen.find("mail", mail).fetch();
        try {
            if (BCrypt.checkpw(password, auth.get(0).password)) {
                this.setAuth(auth.get(0));
                Application.index();
            } else {
                flash.error("Wrong credentials");
                params.flash();
                Validation.keep();
                login();
            }
        } catch (Exception e) {
            flash.error("Wrong credentials");
            params.flash();
            Validation.keep();
            login();
        }


    }

    /**
     * Logout method
     */
    public void logout() {
        this.setAuth(null);
        login();
    }

    /**
     * GET
     * CRUD : Create a citizen
     */
    public static void register() {
        render();
    }

}