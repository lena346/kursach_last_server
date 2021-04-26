//package com.elena.fitnessserver.models;
//
//import javax.persistence.*;
//import java.util.Date;
//import java.util.List;
//import java.util.Set;
//
//@Entity
//public class Client {
//    @Id
//    private long id;
//    @Column(name = "last_name", nullable = false)
//    private String lastName;
//    @Column(name = "first_name", nullable = false)
//    private String firstName;
//    @Column(name = "phone_number", nullable = false)
//    private String phoneNumber;
//    @Column(name = "registration_date", nullable = false)
//    private Date registrationDate;
//
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "clients_instructors",
//            joinColumns = @JoinColumn(name = "client_id"),
//            inverseJoinColumns = @JoinColumn(name = "instructor_id")
//    )
//    private Set<Instructor> instructors;
//
//    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
//    private List<Program> programs;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
//    private List<SeasonTicket> seasonTickets;
//
//
//}
