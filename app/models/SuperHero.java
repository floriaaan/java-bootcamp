package models;

import play.db.jpa.Model;

import javax.persistence.*;

import play.data.validation.*;
import play.data.format.*;

import java.util.*;

import io.ebean.*;

/**
 *
 **/
@Entity(name = "super_heroes")
public class SuperHero extends Model {

    @Required
    public String name;

    @Required
    @OneToOne
    public Citizen identity;

    @Required
    public String power;

    @Required
    public String weakness;

    public int score;

    public String comments;

    public Boolean is_validate = false;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "missions", referencedColumnName = "id")
    public Mission mission;

    public Date created_at;
    public Date updated_at;

    @PrePersist
    public void created_at() {
        this.created_at = new java.util.Date();
        this.updated_at = new java.util.Date();
    }

    @PreUpdate
    public void updated_at() {
        this.updated_at = new java.util.Date();
    }

    public List<Mission> getRelatedMissions() {
        List<Mission> missions = new ArrayList<>();
        List<Mission> all = Mission.all().fetch();

        for(Mission m: all) {
            if(m.super_heroes_list.contains(this)) {
                missions.add(m);
            }
        }
        return missions;
    }
}
