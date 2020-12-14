package models;

import models.Citizen;
import play.data.validation.*;
import play.db.jpa.Model;

import javax.persistence.Entity;


@Entity(name = "super_villains")
public class SuperVillain extends Model {

    @Required
    public String name;

    @Required
    @ManyToOne
    @PrimaryKeyJoinColumn(name="citizen", referencedColumnName="id")
    public Citizen identity;

    @Required
    public String power;

    @Required
    public String weakness;

    public String comments;

    @Required
    public Integer maliciousScore;


}
