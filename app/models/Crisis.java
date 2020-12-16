package models;

import play.db.jpa.Model;

import javax.persistence.*;

import play.data.validation.*;
import play.data.format.*;

import java.util.*;

import io.ebean.*;

import models.Mission;
import models.Dispute;

/**
 *
 **/
@Entity(name = "crises")
public class Crisis extends Model {
    @Required
    public String comments;

    @OneToOne
    public Mission mission;

    @OneToOne
    public Dispute dispute;
}