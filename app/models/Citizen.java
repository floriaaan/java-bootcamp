package models;

import play.db.jpa.Model;
import javax.persistence.Entity;

import play.data.validation.*;

@Entity(name="citizens")
public class Citizen extends Model {
    @Required
    public String fname;
    @Required
    public String lname;

}
