package models;

import play.db.jpa.Model;
import javax.persistence.*;

import play.data.validation.*;
import play.data.format.*;

import java.util.*;
import io.ebean.*;

/**  **/
@Entity(name="satisfactions")
public class Satisfaction extends Model{

    public Date created_at;
    public Date updated_at;

    @OneToOne
    @PrimaryKeyJoinColumn(name="mission", referencedColumnName="id")
    public Mission mission;

    @ManyToMany(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name="superHero", referencedColumnName="id")
    public List<SuperHero> superHeroes = new ArrayList<>();

//    public Long reporter_type;
//
//    public String comments;
//
//    public boolean is_civil;
//    public boolean is_organization;
//    @ManyToOne
//    @PrimaryKeyJoinColumn(name="organization", referencedColumnName="id")
//    public Organization organization_id;

    public int note;

    public String comment;

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
