package models;

import play.db.jpa.Model;

import javax.persistence.*;

import play.data.validation.*;
import play.data.format.*;

import java.util.*;

import io.ebean.*;

import models.Mission;
import models.Dispute;

/**
 *
 **/
@Entity(name = "crises")
public class Crisis extends Model {

    @Required
    public String title;

    @Required
    public String comments;

    @OneToOne
    public Mission mission;

    @OneToOne
    public Dispute dispute;

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
}
