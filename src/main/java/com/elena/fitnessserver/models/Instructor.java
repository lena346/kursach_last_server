package com.elena.fitnessserver.models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "instructors")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "team",nullable = false)
    private String team;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "telephone", nullable = false)
    private String telephone;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "salary", nullable = false)
    private double salary;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "instructor")
    private List<Lesson> lesson;

    @ManyToMany(mappedBy = "instructors")
    private Set<Client> clients;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
