package models;

import play.db.jpa.Model;

import javax.persistence.*;

import play.data.validation.*;

import java.util.*;

@Entity(name = "notifications")
public class Notification extends Model {
    @Required
    @ManyToOne
    @PrimaryKeyJoinColumn(name = "citizens", referencedColumnName = "id")
    public Citizen citizen;

    @Required
    public String comments;
}