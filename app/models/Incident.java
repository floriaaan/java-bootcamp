package models;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 *
 **/
@Entity(name = "incidents")
public class Incident extends Model {

    @Required
    public String title;

    @Required
    public String address;

    public Date created_at;
    public Date updated_at;

    public String state;

    @ManyToOne
    @PrimaryKeyJoinColumn(name = "citizen", referencedColumnName = "id")
    public Citizen citizen;


    public boolean is_organization;


    @OneToOne
    public Mission mission;

    public String comments;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(length = 10000000, columnDefinition = "longblob")
    public byte[] pictureData;

    public String pictureMime;

    @PrePersist
    public void created_at() {
        this.created_at = new java.util.Date();
        this.updated_at = new java.util.Date();
    }

    @PreUpdate
    public void updated_at() {
        this.updated_at = new java.util.Date();
    }

    public Mission getMission(){
        List<Mission> query = Mission.find("incident.id", this.id).fetch();
        if(query.size() > 0) {
            return query.get(0);
        } else {
            return null;
        }
    }
}
