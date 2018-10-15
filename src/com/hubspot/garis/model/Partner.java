package com.hubspot.garis.model;

import java.io.Serializable;

/**
 * @author Garis Suero <garis.suero@gmail.com>,<cuantico@gmail.com>
 */
public class Partner implements Serializable {
    
    private static final long serialVersionUID = 1253402661092342656L;
    
    private int vid;
    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private Long revenue;
    public int getVid() {
        return vid;
    }
    public void setVid(int vid) {
        this.vid = vid;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public Long getRevenue() {
        return revenue;
    }
    public void setRevenue(Long revenue) {
        this.revenue = revenue;
    }
    @Override
    public String toString() {
        return "Partner [vid=" + vid + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", country=" + country + ", revenue=" + revenue + "]";
    }
}
