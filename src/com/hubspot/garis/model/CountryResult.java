package com.hubspot.garis.model;

import java.io.Serializable;
import java.util.Set;

/**
 * @author Garis Suero <garis.suero@gmail.com>,<cuantico@gmail.com>
 */
public class CountryResult implements Serializable {
    private static final long serialVersionUID = 5039925473132140674L;
    private String name;
    private Long partners;
    private Winner winner;
    private Set<String> vips;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getPartners() {
        return partners;
    }
    public void setPartners(Long partners) {
        this.partners = partners;
    }
    public Winner getWinner() {
        return winner;
    }
    public void setWinner(Winner winner) {
        this.winner = winner;
    }
    public Set<String> getVips() {
        return vips;
    }
    public void setVips(Set<String> vips) {
        this.vips = vips;
    }
    
    @Override
    public String toString() {
        return "CountryResult [name=" + name + ", partners=" + partners + ", winner=" + winner + ", vips=" + vips + "]";
    }
}
