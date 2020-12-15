package controllers;

import play.*;
import play.mvc.*;

import java.util.*;
import play.data.validation.*;


import models.*;
import lib.BCrypt;
import java.lang.Object;


public class Authentication extends SuperController {


    public static void login() {
        Citizen c = getAuth();
        render(c);
    }

    public void connect(@Required String mail, @Required String password) {
        if(Validation.hasErrors()) {
            params.flash();
            Validation.keep();
            login();
        }

        List<Citizen> auth = Citizen.find("mail", mail).fetch();
        if(BCrypt.checkpw(password, auth.get(0).password)) {
            this.setAuth(auth.get(0));
            // root();
        } else {
            flash.error("Wrong credentials");
            params.flash();
            Validation.keep();
            login();
        }

    }

    public void logout() {
        this.setAuth(null);
        login();
    }

    // public static Result root() {
    //     return redirect(controllers.routes.Application.index()); 
    // }

}