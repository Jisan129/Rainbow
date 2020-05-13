package com.example.rainbow;


import com.example.rainbow.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Current {

    private String locationLebel;
    private String icon;
    private long time;
    private double temperature;
    private double precipChance;
    private String summary;
    private String timezone;


    public Current() {
    }

    public Current(String locationLebel, String icon, long time, double temperature, double precipChance, String summary, String timezone) {
        this.locationLebel = locationLebel;
        this.icon = icon;
        this.time = time;
        this.temperature = temperature;
        this.precipChance = precipChance;
        this.summary = summary;
        this.timezone = timezone;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getLocationLebel() {
        return locationLebel;
    }

    public void setLocationLebel(String locationLebel) {
        this.locationLebel = locationLebel;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    public int getIconId(){
        //clear-day, clear-night, rain, snow, sleet, wind, fog, cloudy, partly-cloudy-day, or partly-cloudy-night


        int id= R.drawable.clear_day;

        switch (icon){
            case "clear-day":
                id=R.drawable.clear_day;
                break;
            case "clear-night":
                id=R.drawable.clear_night;
                break;
            case "rain":
                id=R.drawable.rain;
                break;
            case "snow":
                id=R.drawable.snow;
                break;
            case "sleet":
                id=R.drawable.sleet;
                break;
            case "wind":
                id=R.drawable.wind;
                break;
            case "cloudy":
                id =R.drawable.cloudy;
                break;

            case "partly-cloudy-day":
                id =R.drawable.partly_cloudy;
                break;

            case "partly-cloudy-night":
                id=R.drawable.cloudy_night;
                break;
        }

        return id;
    }



    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String TimeFormat(){
        SimpleDateFormat formater=new SimpleDateFormat("hh:mm a");
        formater.setTimeZone(TimeZone.getTimeZone(timezone));

        Date datetime=new Date(time*1000);
        return formater.format(datetime);

    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getPrecipChance() {
        return precipChance;
    }

    public void setPrecipChance(double precipChance) {
        this.precipChance = precipChance;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }



}
