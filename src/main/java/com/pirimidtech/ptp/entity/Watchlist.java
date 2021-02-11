package com.pirimidtech.ptp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "watchlist")
public class Watchlist {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private User user;

    private String name;

    private String description;
}