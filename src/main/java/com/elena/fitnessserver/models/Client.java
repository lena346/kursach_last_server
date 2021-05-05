package com.elena.fitnessserver.models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "team",nullable = false)
    private String teamClient;

    @Column(name = "last_name", nullable = false)
    private String lastNameClient;

    @Column(name = "first_name", nullable = false)
    private String firstNameClient;

    @Column(name = "telephone", nullable = false)
    private String telephoneClient;

    @Column(name = "age",nullable = false)
    private int ageClient;

    @Column(name = "date", nullable = false)
    private String dateClient;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<Program> programs;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "clients_instructors",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "instructor_id")
    )
    private Set<Instructor> instructors;

    public void setInstructors(Set<Instructor> instructors) {
        this.instructors = instructors;
    }

    public Set<Instructor> getInstructors() {
        return instructors;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    public List<Program> getPrograms() {
        return programs;
    }

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

