package com.Dhiraj.Online.Food.ordering.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String image;

    private String startedAt;

    private String endsAt;

    private String name;

    @ManyToOne
    private Restaurant restaurant;

    private String location;
}
