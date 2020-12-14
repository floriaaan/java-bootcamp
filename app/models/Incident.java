package models;

import play.db.jpa.Model;
import javax.persistence.*;

import play.data.validation.*;
import play.data.format.*;

import java.util.*;
import io.ebean.*;

/**  **/
@Entity(name="incidents")
public class Incident extends Model{

    @Required
    public String title;

    @Required
    public String address;

    public Date created_at;
    public Date updated_at;

    @ManyToOne
    @PrimaryKeyJoinColumn(name="citizen", referencedColumnName="id")
    public Citizen citizen_id;

//    public Long reporter_type;
//
//    public String comments;
//
//    public boolean is_civil;
//    public boolean is_organization;
}
