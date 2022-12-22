package com.packagedeliverycompanyspringboot.controllers;

import com.google.gson.Gson;
import com.packagedeliverycompanyspringboot.errors.ManagerNotFound;
import com.packagedeliverycompanyspringboot.model.Manager;
import com.packagedeliverycompanyspringboot.model.User;
import com.packagedeliverycompanyspringboot.repasitory.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Properties;

import static org.springframework.hateoas.server.core.WebHandler.linkTo;

@RestController
public class ManagerController {
    @Autowired
    private ManagerRepository managerRepository;


    public ManagerController(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @GetMapping(value = "/allManagers")
    public @ResponseBody Iterable<Manager> getAllManagers() {
        return managerRepository.findAll();
    }

    @PostMapping(value = "/validateManager")
    public @ResponseBody Manager validateManager(@RequestBody String info) {
        Gson parser = new Gson();
        Properties properties = parser.fromJson(info, Properties.class);
        return managerRepository.findManagerByLoginAndPassword(properties.getProperty("login"),
                properties.getProperty("password"));
    }

    @DeleteMapping(value = "/deleteManager/{id}")
    public @ResponseBody String deleteManager(@PathVariable(name = "id") int id) {
        managerRepository.deleteById(id);
        return "Completed";
    }

    @GetMapping(value = "/managerById/{id}")
    public @ResponseBody Manager geManagerById(@PathVariable(name = "id") int id) {
        return managerRepository.findById(id).orElseThrow(() -> new ManagerNotFound(id));
    }
}
