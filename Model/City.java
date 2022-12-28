package org.example.Model;


public class City {

    private int cityId;
    private String name;

    public String getName() {
        return name;
    }

    public City(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCityId() {return cityId;}

    public void setCityId(int cityId) {this.cityId = cityId;}
}
