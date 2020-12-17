package models;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

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
