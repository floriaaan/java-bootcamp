package models;

import play.db.jpa.Model;

import javax.persistence.*;

import play.data.validation.*;
import play.data.format.*;

import java.util.*;

import io.ebean.*;

import models.Mission;
import models.Citizen;
import models.SuperVillain;

@Entity(name = "missions_reports")
public class MissionReport extends Model {

    @Required
    public String title;

    @Required
    public String details;

    @ManyToMany(cascade = CascadeType.ALL)
    public List<SuperVillain> superVillainList = new ArrayList<>();

    @Required
    @OneToOne
    public Mission mission;

    public Date created_at;
    public Date updated_at;

    @Required
    @OneToOne
    public Citizen interlocutor;

    @PrePersist
    public void created_at() {
        this.created_at = new java.util.Date();
        this.updated_at = new java.util.Date();
    }

    @PreUpdate
    public void updated_at() {
        this.updated_at = new java.util.Date();
    }


}
