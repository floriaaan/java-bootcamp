package controllers;

import play.*;
import play.mvc.*;

import java.util.*;
import play.data.validation.*;

import models.*;


public class SuperController extends Controller {

    public SuperController()
    {
        super();
    }
 
    private static Citizen INSTANCE;
     
    public static Citizen getAuth()
    {   
        return INSTANCE;
    }

    public static void setAuth(Citizen c)
    {   
        INSTANCE = c;
    }

}