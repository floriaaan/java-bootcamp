package models;

import play.db.jpa.Model;
import javax.persistence.*;

import play.data.validation.*;
import play.data.format.*;

import java.util.*;
import io.ebean.*;

/**  **/
@Entity(name="super_heroes")
public class SuperHero extends Model{

    @Required
    public String name;

    @Required
    //@ManyToOne
    @PrimaryKeyJoinColumn(name="citizen", referencedColumnName="id")
    public Citizen identity;

    @Required
    public String power;

    @Required
    public String weakness;

    public int score;

    public String comments;

    @ManyToOne
    public Mission mission;
}
