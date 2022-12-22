package com.packagedeliverycompanyspringboot.controllers;

import com.packagedeliverycompanyspringboot.errors.CargoNotFound;
import com.packagedeliverycompanyspringboot.errors.CheckpointNotFound;
import com.packagedeliverycompanyspringboot.model.Cargo;
import com.packagedeliverycompanyspringboot.model.Checkpoint;
import com.packagedeliverycompanyspringboot.repasitory.CheckpointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CheckpointController {
    @Autowired
    private CheckpointRepository checkpointRepository;

    public CheckpointController(CheckpointRepository checkpointRepository) {
        this.checkpointRepository = checkpointRepository;
    }

    @GetMapping(value = "/allCheckpoints")
    public @ResponseBody Iterable<Checkpoint> getAllCheckpoints() {
        return this.checkpointRepository.findAll();
    }

    @PostMapping(value = "/addCheckpoint")
    public @ResponseBody Checkpoint addCheckpoint(@RequestBody Checkpoint checkpoint) {
        checkpointRepository.save(checkpoint);
        return checkpoint;
    }

    @GetMapping(value = "/checkpoint/{id}")
    EntityModel<Checkpoint> one(@PathVariable Integer id) {
        Checkpoint checkpoint = checkpointRepository.findById(id).orElseThrow(() -> new CheckpointNotFound(id));
        return EntityModel.of(checkpoint, linkTo(methodOn(CheckpointController.class).one(id)).withSelfRel(),
                linkTo(methodOn(CheckpointController.class).getAllCheckpoints()).withRel("checkpoints"));
    }

    @PutMapping(value = "/validateCheckpoint")
    public @ResponseBody Checkpoint validateCheckpoint(@RequestBody Checkpoint checkpoint) {
        return checkpointRepository.findCheckpointByAddress(checkpoint.getAddress());
    }

    @DeleteMapping(value = "deleteCheckpoint/{id}")
    public @ResponseBody String deleteCheckpoint(@PathVariable(name = "id") int id) {
        checkpointRepository.deleteById(id);
        System.out.println(checkpointRepository.findById(id));
        return "Completed";
    }
}
