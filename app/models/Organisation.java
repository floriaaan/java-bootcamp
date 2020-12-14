package models;

import play.db.jpa.Model;
import javax.persistence.Entity;

import play.data.validation.*;

import java.util.*;
import models.Citizen;

@Entity(name = "organisations")
public class Organisation extends Model {

    @Required
    public String name;

    @Required
    public String address;

    @Required
    public Citizen boss;

    //public List<Citizen> members;

    public String comments;

    public Date created_at;
    public Date updated_at;

    public Integer reported_incidents;

    public Integer implicated;

}