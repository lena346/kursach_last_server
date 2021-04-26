//package com.elena.fitnessserver.models;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Table(name = "season_tickets")
//public class SeasonTicket {
//
//    @Id
//    private long id;
//
//    @Column(nullable = false)
//    private String name;
//
//    private int discount;
//
//    @ManyToOne
//    @JoinColumn(name = "client_id", nullable = false)
//    private Client client;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "seasonTicket")
//    private List<Program> programs;
//}
