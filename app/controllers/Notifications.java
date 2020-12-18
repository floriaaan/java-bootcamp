package controllers;

import middlewares.Rights;
import models.Citizen;
import models.Notification;

import java.util.List;

/**
 * Notification Controller
 */
public class Notifications extends Rights {
    /**
     * GET
     * CRUD : Read all Notifications
     */
    public static void showAll() {
        Citizen auth = getAuth();
        List<Notification> read = Notification.find("citizen.id = ?1 and is_viewed = 1", auth.id).fetch();


        List<Notification> unread = Notification.find("citizen.id = ?1 and is_viewed = 0", auth.id).fetch();

        for (Notification n : unread) {
            n.is_viewed = true;
            n.save();
        }
        render(unread, read);
    }
}