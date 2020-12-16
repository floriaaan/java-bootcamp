package models;

import play.db.jpa.Model;

import javax.persistence.*;

import play.data.validation.*;
import play.data.format.*;

import java.util.*;

import io.ebean.*;
import models.Incident;
import models.Citizen;

@Entity(name="missions")
public class Mission extends Model {

    @Required
    public String type;

    @Required
    public String title;

    @Required
    public Date start_date;

    @OneToOne
    public Incident incident;

    @OneToOne
    public Crisis crisis;

    public Date end_date;

    @Required
    public String address;

    public String informations;

    @Required
    @ManyToMany(cascade = CascadeType.ALL)
    public List<SuperHero> super_heroes_list = new ArrayList<>();

    @Required
    public int gravity_level;

    @Required
    public String emergency_level;

    public Date created_at;
    public Date updated_at;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="mission")
    public List<Dispute> dispute_list;

    public String state;

    public String getName(){
        return this.title;
    }

    @PrePersist
    public void created_at() {
        this.created_at = new java.util.Date();
        this.updated_at = new java.util.Date();
    }

    @PreUpdate
    public void updated_at() {
        this.updated_at = new java.util.Date();
    }

    public Long getIncidentId(){
        return this.incident.id;
    }

}
