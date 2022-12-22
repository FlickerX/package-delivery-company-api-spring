package com.packagedeliverycompanyspringboot.controllers;

import com.packagedeliverycompanyspringboot.errors.TruckNotFound;
import com.packagedeliverycompanyspringboot.model.Cargo;
import com.packagedeliverycompanyspringboot.model.Destination;
import com.packagedeliverycompanyspringboot.model.Truck;
import com.packagedeliverycompanyspringboot.repasitory.TruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class TruckController {
    @Autowired
    private TruckRepository truckRepository;

    public TruckController(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    @PutMapping(value = "/validateTruck")
    public @ResponseBody Truck validateTruck(@RequestBody Truck truck) {
        return truckRepository.findTruckByMarkAndModel(truck.getMark(), truck.getModel());
    }

    @PostMapping(value = "/addTruck")
    public @ResponseBody Truck addTruck(@RequestBody Truck truck) { // TODO: Maybe add validation
        System.out.println(truck.getMark() + " " + truck.getModel() + " " + truck.getColor());
        truckRepository.save(truck);
        return truck;
    }

    @GetMapping(value = "/allTrucks")
    public @ResponseBody Iterable<Truck> getAll() {
        return truckRepository.findAll();
    }

    @GetMapping(value = "/truck/{id}")
    EntityModel<Truck> one(@PathVariable Integer id) {
        Truck truck = truckRepository.findById(id).orElseThrow(() -> new TruckNotFound(id));
        return EntityModel.of(truck, linkTo(methodOn(TruckController.class).one(id)).withSelfRel(),
                linkTo(methodOn(TruckController.class).getAll()).withRel("trucks"));
    }

    @DeleteMapping(value = "/deleteTruck/{id}")
    public @ResponseBody String deleteTruck(@PathVariable(name = "id") int id) {
        truckRepository.deleteById(id);
        return "Completed";
    }
}
