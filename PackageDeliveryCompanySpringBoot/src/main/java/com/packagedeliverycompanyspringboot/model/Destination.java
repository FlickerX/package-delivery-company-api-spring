package com.packagedeliverycompanyspringboot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.packagedeliverycompanyspringboot.Enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Destination {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String address;
    private LocalDate requestedDeliveryDate;
    private LocalDate deliveryStartDate;

    private LocalDate deliveryEndDate;
    private OrderStatus status;
    @JsonIgnore
    @ManyToMany
    private List<Manager> managers;
    @JsonIgnore
    @ManyToMany
    private List<Cargo> cargos;
    @JsonIgnore
    @ManyToMany
    private List<Checkpoint> checkpoints;
    @JsonIgnore
    @ManyToOne
    private Courier courier;
    @ManyToOne
    private Truck truck;
    public Destination(String address, LocalDate requestedDeliveryDate, LocalDate deliveryStartDate, OrderStatus status, List<Manager> managers, List<Cargo> cargos, Courier courier, Truck truck) {
        this.address = address;
        this.requestedDeliveryDate = requestedDeliveryDate;
        this.deliveryStartDate = deliveryStartDate;
        this.status = status;
        this.managers = managers;
        this.cargos = cargos;
        this.courier = courier;
        this.truck = truck;
    }

    public Destination(String address, LocalDate requestedDeliveryDate, LocalDate deliveryStartDate, OrderStatus status, List<Manager> managers, List<Cargo> cargos, Truck truck) {
        this.address = address;
        this.requestedDeliveryDate = requestedDeliveryDate;
        this.deliveryStartDate = deliveryStartDate;
        this.status = status;
        this.managers = managers;
        this.cargos = cargos;
        this.truck = truck;
    }

    @Override
    public String toString() {
        return "Address = " + address +
                ", requestedDeliveryDate=" + requestedDeliveryDate +
                " " + status +
                ", courier=" + courier +
                ", truck=" + truck;
    }
}
