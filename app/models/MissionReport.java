package models;

import play.db.jpa.Model;

import javax.persistence.*;

import play.data.validation.*;
import play.data.format.*;

import java.util.*;

import io.ebean.*;

import models.Mission;
import models.Citizen;
import models.SuperVillain;

@Entity(name = "missions_reports")
public class MissionReport extends Model {

    @Required
    public String title;

    @Required
    public String details;

    @ManyToMany(cascade = CascadeType.ALL)
    public List<SuperVillain> superVillainList = new ArrayList<>();

    @Required
    @OneToOne
    public Mission mission;

    @Required
    @OneToOne
    public Citizen interlocutor;


}
