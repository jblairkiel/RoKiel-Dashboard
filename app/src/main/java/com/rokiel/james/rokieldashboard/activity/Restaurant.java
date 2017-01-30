package com.rokiel.james.rokieldashboard.activity;

/**
 * Created by James on 1/28/2017.
 */

public class Restaurant {

    int ID;
    String RestaurantName;
    String Address;
    String Type;
    String Price;
    String SpinnerGroup;

     public Restaurant(int id, String resName, String resAddress, String resType, String resPrice, String resSpinnerGroup){
        this.ID = id;
        this.RestaurantName = resName;
        this.Address = resAddress;
        this.Type = resType;
        this.Price = resPrice;
        this.SpinnerGroup = resSpinnerGroup;
    }
}
