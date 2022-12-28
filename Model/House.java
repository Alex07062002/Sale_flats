package org.example.Model;

import java.util.Date;

public class House {

    private int houseId;
    private int cityId;
    private String address;
    private String name;
    private Date constructionStartDate;
    private Date constructionCompletionDate;

    private Date commissioning;

    public int getCityId() {
        return cityId;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public Date getConstructionStartDate() {
        return constructionStartDate;
    }

    public Date getCommissioning() {
        return commissioning;
    }

    public Date getConstructionCompletionDate() {
        return constructionCompletionDate;
    }

    public House (int cityID, String address, String name, Date constructionStartDate, Date constructionCompletionDate, Date commissioning) {
        this.cityId = cityID;
        this.address = address;
        this.name = name;
        this.constructionStartDate = constructionStartDate;
        this.constructionCompletionDate = constructionCompletionDate;
        this.commissioning = commissioning;
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(int houseId) {
        this.houseId = houseId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setConstructionStartDate(Date constructionStartDate) {
        this.constructionStartDate = constructionStartDate;
    }

    public void setConstructionCompletionDate(Date constructionCompletionDate) {
        this.constructionCompletionDate = constructionCompletionDate;
    }

    public void setCommissioning(Date commissioning) {
        this.commissioning = commissioning;
    }
}
