

package com.sblinn.usermessageapplication.dto;

/**
 *
 * @author Sara Blinn
 */
public class Company {

    private int id;
    
    private String company;
    
    private String city;
    
    private String timezone;

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    
    @Override
    public String toString() {
        return "Company{" + "id=" + id + ", company=" + company 
                + ", city=" + city + ", timezone=" + timezone + '}';
    }
    
}
