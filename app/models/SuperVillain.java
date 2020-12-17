package models;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity(name = "super_villains")
public class SuperVillain extends Model {

    @Required
    public String name;

    @OneToOne
    public Citizen identity;

    @Required
    public String power;

    @Required
    public String weakness;

    public String comments;

    public Date created_at;
    public Date updated_at;

    @Required
    public Integer malicious_score;

    @ManyToMany(cascade = CascadeType.ALL)
    public List<MissionReport> mission_reportList = new ArrayList<>();

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
