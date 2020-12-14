package models;

import play.db.jpa.Model;
import javax.persistence.*;

import play.data.validation.*;

import java.util.*;
import models.Citizen;

@Entity(name = "organizations")
public class Organization extends Model {

    @Required
    public String name;

    @Required
    public String address;

    @Required
    @ManyToOne
    @PrimaryKeyJoinColumn(name="citizen", referencedColumnName="id")
    public Citizen boss;

    @ManyToMany(cascade = CascadeType.ALL)
    public List<Citizen> members = new ArrayList<>();

    public String comments;

    public Date created_at;
    public Date updated_at;

    public Integer reported_incidents;

    public Integer implicated;

}
