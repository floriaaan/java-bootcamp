package models;

import play.db.jpa.Model;
import javax.persistence.*;

import play.data.validation.*;
import play.data.format.*;

import java.util.*;
import io.ebean.*;

@Entity(name="missions")
public class Mission extends Model {

    @Required
    public String type;

    @Required
    public String title;

    @Required
    public Date startedDate;

    public Date enddedDate;

    @Required
    public String address;

    public String informartions;

    @Required
    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    public List<SuperHero> superHeroList = new ArrayList<>();

    @Required
    public Integer gravityLevel;

    @Required
    public Integer emergencyLevel;


}