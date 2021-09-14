package com.zoracorp.states.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "persons")
public class Persons {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="phn_nmbr", nullable=false, length=20)
    private String number;
    @Column(name="f_name", nullable=false, length=200)
    private String firstName;
    @Column(name="l_name", nullable=false, length=200)
    private String lastName;
    @Column(name="cntry", nullable=false, length=200)
    private String country;
    @Column(name="a_state ", nullable=false, length=200)
    private String state;
    @Column(name="lga", nullable=false, length=200)
    private String LGA;
    @Column(name="gndr", nullable=false, length=200)
    private String gender;
}
