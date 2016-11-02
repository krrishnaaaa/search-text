package com.pcsalt.example.searchtext.model.state;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Navkrishna on 02 November, 2016
 */

public class Result {

    @SerializedName("country")
    private String country;

    @SerializedName("name")
    private String name;

    @SerializedName("abbr")
    private String abbr;

    @SerializedName("area")
    private String area;

    @SerializedName("largest_city")
    private String largestCity;

    @SerializedName("capital")
    private String capital;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getLargestCity() {
        return largestCity;
    }

    public void setLargestCity(String largestCity) {
        this.largestCity = largestCity;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
}
