package models;

import play.db.jpa.Model;
import play.data.validation.*;
import io.ebean.*;
import java.util.*;
import javax.persistence.*;
import play.data.format.*;

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
    @Required
    public String phone;

    @Required
    public Date birthdate;
    public String deathdate;
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
}
