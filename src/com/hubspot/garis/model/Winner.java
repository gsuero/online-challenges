package com.hubspot.garis.model;

import java.io.Serializable;

public class Winner implements Serializable {
    private static final long serialVersionUID = -3530940550957570786L;
    
    private String email;
    private Long amount;
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Long getAmount() {
        return amount;
    }
    public void setAmount(Long amount) {
        this.amount = amount;
    }
    @Override
    public String toString() {
        return "Winner [email=" + email + ", amount=" + amount + "]";
    }
    
    
}
