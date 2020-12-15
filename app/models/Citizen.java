package models;

import play.db.jpa.Model;
import play.data.validation.*;
import io.ebean.*;
import java.util.*;
import javax.persistence.*;
import play.data.format.*;

import play.db.ebean.*;
import com.avaje.ebean.*;

import lib.BCrypt;


@Entity(name = "citizens")
public class Citizen extends Model {

    @Required
    public String fname;
    @Required
    public String lname;

    @Required
    public String sex;

    @Required
    public String address;
    @Required
    public String mail;
    public String password;
    @Required
    public String phone;

    @Required
    public Date birthdate;
    public Date deathdate;
    @Required
    public String nationality;

    @ManyToOne
    @PrimaryKeyJoinColumn(name="organization", referencedColumnName="id")
    public Organization organization;

    public String comments;

    public Date created_at;
    public Date updated_at;

    public Integer reported_incidents;

    public Integer victim;

    public Boolean is_superuser;

    public Boolean is_authority;

    public String getName(){
        return this.fname + " " + this.lname;
    }

    @PrePersist
    public void created_at() {
        this.password = BCrypt.hashpw(this.password, BCrypt.gensalt());
        this.created_at = new java.util.Date();
        this.updated_at = new java.util.Date();
    }

    @PreUpdate
    public void updated_at() {
        this.updated_at = new java.util.Date();
    }
}
