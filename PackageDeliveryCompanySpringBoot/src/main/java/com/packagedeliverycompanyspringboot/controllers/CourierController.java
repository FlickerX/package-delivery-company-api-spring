package com.packagedeliverycompanyspringboot.controllers;

import com.google.gson.Gson;
import com.packagedeliverycompanyspringboot.errors.CargoNotFound;
import com.packagedeliverycompanyspringboot.model.Cargo;
import com.packagedeliverycompanyspringboot.model.Courier;
import com.packagedeliverycompanyspringboot.repasitory.CourierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.Properties;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CourierController {
    @Autowired
    private CourierRepository courierRepository;

    public CourierController(CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
    }

    @GetMapping(value = "/allCouriers")
    public @ResponseBody Iterable<Courier> getAllCouriers() {
        return courierRepository.findAll();
    }

    @PostMapping(value = "/addCourier")
    public @ResponseBody Courier addCourier(@RequestBody Courier courier) {
        courierRepository.save(courier);
        return courier;
    }

    @GetMapping(value = "/courier/{id}")
    EntityModel<Courier> one(@PathVariable Integer id) {
        Courier courier = courierRepository.findById(id).orElseThrow(() -> new CargoNotFound(id));
        return EntityModel.of(courier, linkTo(methodOn(CourierController.class).one(id)).withSelfRel(),
                linkTo(methodOn(CourierController.class).getAllCouriers()).withRel("courier"));
    }


    @PostMapping(value = "/validateCourier")
    public @ResponseBody Courier validateCourier(@RequestBody String info) {
        Gson parser = new Gson();
        Properties properties = parser.fromJson(info, Properties.class);
        return courierRepository.findCourierByLoginAndPassword(properties.getProperty("login"),
                properties.getProperty("password"));
    }
    @DeleteMapping(value = "/deleteCourier/{id}")
    public @ResponseBody String deleteCourier(@PathVariable(name = "id") int id) {
        courierRepository.deleteById(id);
        return "Completed";
    }
}
