package com.tusharmalik.playersearch;

import java.io.Serializable;

/**
 * Created by tushm on 13-01-2019.
 */

public class Player implements Serializable {
   public String Name;
   public String Country;
   public String Type;
   public int id;
    public Player(){}
   public Player(int id,String name,String country,String type){
       this.id=id;
       this.Name=name;
       this.Country=country;
       this.Type=type;

   }
    public String getName() {
        return Name;
    }
    public String getCountry() {
        return Country;
    }
    public String getType() {
        return Type;
    }

}
