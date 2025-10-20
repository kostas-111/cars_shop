package ru.job4j.cars.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "auto_user")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "login", nullable = false, unique = true)
    @EqualsAndHashCode.Include
    private String login;

    @Column(name = "password", nullable = false)
    private String password;
}
