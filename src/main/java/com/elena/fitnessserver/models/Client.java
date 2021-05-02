package com.elena.fitnessserver.models;

import javax.persistence.*;


@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String teamClient;

    @Column(name = "last_name", nullable = false)
    private String lastNameClient;

    @Column(name = "first_name", nullable = false)
    private String firstNameClient;

    @Column(nullable = false)
    private String telephoneClient;

    @Column(nullable = false)
    private int ageClient;

    @Column(nullable = false)
    private String dateClient;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeamClient() {
        return teamClient;
    }

    public void setTeamClient(String teamClient) {
        this.teamClient = teamClient;
    }

    public String getLastNameClient() {
        return lastNameClient;
    }

    public void setLastNameClient(String lastNameClient) {
        this.lastNameClient = lastNameClient;
    }

    public String getFirstNameClient() {
        return firstNameClient;
    }

    public void setFirstNameClient(String firstNameClient) {
        this.firstNameClient = firstNameClient;
    }

    public String getTelephoneClient() {
        return telephoneClient;
    }

    public void setTelephoneClient(String telephoneClient) {
        this.telephoneClient = telephoneClient;
    }

    public int getAgeClient() {
        return ageClient;
    }

    public void setAgeClient(int ageClient) {
        this.ageClient = ageClient;
    }

    public String getDateClient() {
        return dateClient;
    }

    public void setDateClient(String dateClient) {
        this.dateClient = dateClient;
    }
}

