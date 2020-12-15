package controllers;

import play.*;
import play.mvc.*;
import play.mvc.Scope.*;

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


    @Before
    public void middleware_Auth() {
        if(this.INSTANCE == null) {
            // System.out.println("unauth");
            // Redirect to login
        } else {
            renderArgs.put("user", this.INSTANCE);
        }
    }

}