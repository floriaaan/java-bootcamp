package models;

import play.data.validation.*;
import play.db.jpa.Model;
import play

import javax.persistence.Entity;
import models.Citizen;


@Entity(name = "super_villain")
public class SuperVillain extends Model {

    @Required
    public String name;

    public Citizen identity;

    @Required
    public string power;

    @Required
    public string weakness;

    public String comments;

    @Required
    public Integer malicious-score


}
