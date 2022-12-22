package com.packagedeliverycompanyspringboot.controllers;

import com.packagedeliverycompanyspringboot.errors.CargoNotFound;
import com.packagedeliverycompanyspringboot.errors.DestinationNotFound;
import com.packagedeliverycompanyspringboot.model.Cargo;
import com.packagedeliverycompanyspringboot.model.Destination;
import com.packagedeliverycompanyspringboot.repasitory.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class DestinationController {
    @Autowired
    private DestinationRepository destinationRepository;

    public DestinationController(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    @GetMapping(value = "allDestinations")
    public @ResponseBody Iterable<Destination> getAllDestinations() {
        return this.destinationRepository.findAll();
    }

    @PostMapping(value = "/addDestination")
    public @ResponseBody Destination addDestination(@RequestBody Destination destination) {
        destinationRepository.save(destination);
        return destination;
    }

    @PutMapping(value = "/updateDestination")
    public @ResponseBody Destination updateDestination(@RequestBody Destination destination) {
        destinationRepository.save(destination);
        return destination;
    }

    @GetMapping(value = "/destination/{id}")
    EntityModel<Destination> one(@PathVariable Integer id) {
        Destination destination = destinationRepository.findById(id).orElseThrow(() -> new DestinationNotFound(id));
        return EntityModel.of(destination, linkTo(methodOn(DestinationController.class).one(id)).withSelfRel(),
                linkTo(methodOn(DestinationController.class).getAllDestinations()).withRel("destinations"));
    }

    @PutMapping(value = "/validateDestination")
    public @ResponseBody Destination validateDestination(@RequestBody Destination destination) {
        return destinationRepository.findDestinationByAddress(destination.getAddress());
    }

    @DeleteMapping(value = "/deleteDestination/{id}")
    public @ResponseBody String deleteDestination(@PathVariable(name = "id") int id) {
        destinationRepository.deleteById(id);
        return "Completed";
    }
}
