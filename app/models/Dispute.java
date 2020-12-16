package models;

import play.db.jpa.Model;
import play.data.validation.*;
import io.ebean.*;
import java.util.*;
import javax.persistence.*;
import play.data.format.*;

import play.db.ebean.*;
import com.avaje.ebean.*;

import models.Crisis;

@Entity(name="disputes")
public class Dispute extends Model {

    @Required
    public String title;

    @OneToOne
    public Mission mission;

    @OneToOne
    public Citizen citizen;

    @OneToOne
    public Crisis crisis;

    public String comments;

    @Required
    public float amount;

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
