package controllers;

import play.*;
import play.mvc.*;

import play.data.validation.*;

import java.util.*;
import middlewares.Rights;

import models.Citizen;
import models.Notification;

public class Notifications extends Rights {
    /**
     * GET
     * CRUD : Read all Notifications
     */
    public static void showAll() {
        Citizen auth = getAuth();
        List<Notification> nList = Notification.find("citizen.id", auth.id).fetch();
        render(nList);
    }
}