package com.packagedeliverycompanyspringboot.controllers;

import com.packagedeliverycompanyspringboot.errors.CargoNotFound;
import com.packagedeliverycompanyspringboot.model.Cargo;
import com.packagedeliverycompanyspringboot.model.Truck;
import com.packagedeliverycompanyspringboot.repasitory.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CargoController {
    @Autowired
    private CargoRepository cargoRepository;

    public CargoController(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    @GetMapping(value = "/allCargos")
    public @ResponseBody Iterable<Cargo> getAllCargos() {
        return cargoRepository.findAll();
    }

    @PostMapping(value = "/addCargo")
    public @ResponseBody Cargo addCargo(@RequestBody Cargo cargo) {
        cargoRepository.save(cargo);
        return cargo;
    }

    @GetMapping(value = "/cargo/{id}")
    EntityModel<Cargo> one(@PathVariable Integer id) {
        Cargo cargo = cargoRepository.findById(id).orElseThrow(() -> new CargoNotFound(id));
        return EntityModel.of(cargo, linkTo(methodOn(CargoController.class).one(id)).withSelfRel(),
                linkTo(methodOn(CargoController.class).getAllCargos()).withRel("cargos"));
    }

    @PutMapping(value = "/validateCargo")
    public @ResponseBody Cargo validateCargo(@RequestBody Cargo cargo) {
        return cargoRepository.findCargoByNaming(cargo.getNaming());
    }

    @DeleteMapping(value = "deleteCargo/{id}")
    public @ResponseBody String deleteCargo(@PathVariable(name = "id") int id) {
        cargoRepository.deleteById(id);
        return "Completed";
    }
}
