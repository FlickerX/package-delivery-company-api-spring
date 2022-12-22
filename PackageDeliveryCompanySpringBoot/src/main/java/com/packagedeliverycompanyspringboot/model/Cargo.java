package com.packagedeliverycompanyspringboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String naming;

    private double weight;

    @JsonIgnore
    @ManyToMany(mappedBy = "cargos", cascade = {CascadeType.ALL})
    private List<Destination> destinations;

    public Cargo(String naming, double weight) {
        this.naming = naming;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Title = " + naming +
                ", Weight = " + weight;
    }
}
