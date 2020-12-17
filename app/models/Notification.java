package models;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "notifications")
public class Notification extends Model {
    @Required
    public String title;

    @Required
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "citizens", referencedColumnName = "id")
    public Citizen citizen;

    @Required
    public String comments;

    @Required
    public Boolean is_viewed = false;
}