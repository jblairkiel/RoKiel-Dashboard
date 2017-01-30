package com.rokiel.james.rokieldashboard.activity;

/**
 * Created by James on 1/28/2017.
 */

public class RestaurantSubmission {

    int ID;
    String RestaurantName;
    String Location;
    String Status;

     public RestaurantSubmission(int id, String resName, String resLocation, String resStatus){
        this.ID = id;
        this.RestaurantName = resName;
        this.Location = resLocation;
        this.Status = resStatus;
    }
}
