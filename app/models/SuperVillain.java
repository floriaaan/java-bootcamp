package models;

import models.Citizen;
import play.data.validation.*;
import play.db.jpa.Model;

import javax.persistence.Entity;


@Entity(name = "super_villain")
public class SuperVillain extends Model {

    @Required
    public String name;

    public Citizen identity;

    @Required
    public String power;

    @Required
    public String weakness;

    public String comments;

    @Required
    public Integer maliciousScore;


}
