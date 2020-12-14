package models;

import models.Citizen;

import play.data.validation.*;
import play.db.jpa.Model;
import play.data.format.*;
import io.ebean.*;

import javax.persistence.*;
import java.util.*;


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
