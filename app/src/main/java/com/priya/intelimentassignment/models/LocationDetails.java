package com.priya.intelimentassignment.models;

import java.util.HashMap;

/**
 *
 * This is a POJO class containing all the information about the location
 *
 * @author Priyanka P
 * @see
 *
 * @createdOn March 19, 2016
 *
 * @modifiedOn
 *
 *
 */
public class LocationDetails {

    private String id;
    private String name;
    private String latitude;
    private String longitude;
    private HashMap<String,String> modeOfTrasnportationWithRequiedTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public HashMap<String, String> getModeOfTrasnportationWithRequiedTime() {
        return modeOfTrasnportationWithRequiedTime;
    }

    public void setModeOfTrasnportationWithRequiedTime(HashMap<String, String> modeOfTrasnportationWithRequiedTime) {
        this.modeOfTrasnportationWithRequiedTime = modeOfTrasnportationWithRequiedTime;
    }

    @Override
    public String toString() {
        return "\nid = "+id+", name ="+name+" Location:("+latitude+","+longitude+"), MAP :"+modeOfTrasnportationWithRequiedTime+"\n";
    }
}
