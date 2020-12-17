package models;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @PrePersist
    public void created_at() {
        this.created_at = new java.util.Date();
        this.updated_at = new java.util.Date();
    }

    @PreUpdate
    public void updated_at() {
        this.updated_at = new java.util.Date();
    }

    public Integer reported_incidents;

    public Integer implicated;

}
