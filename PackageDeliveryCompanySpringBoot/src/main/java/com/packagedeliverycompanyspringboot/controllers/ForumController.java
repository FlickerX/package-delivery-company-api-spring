package com.packagedeliverycompanyspringboot.controllers;

import com.packagedeliverycompanyspringboot.errors.CargoNotFound;
import com.packagedeliverycompanyspringboot.errors.ForumNotFound;
import com.packagedeliverycompanyspringboot.model.Cargo;
import com.packagedeliverycompanyspringboot.model.Forum;
import com.packagedeliverycompanyspringboot.repasitory.ForumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ForumController {
    @Autowired
    private ForumRepository forumRepository;

    public ForumController(ForumRepository forumRepository) {
        this.forumRepository = forumRepository;
    }

    @GetMapping(value = "/allForums")
    public @ResponseBody Iterable<Forum> getAllForums() {
        return this.forumRepository.findAll();
    }

    @DeleteMapping(value = "deleteForum/{id}")
    public @ResponseBody String deleteForum(@PathVariable(name = "id") int id) {
        forumRepository.deleteById(id);
        return "Completed";
    }

    @PostMapping(value = "/addForum")
    public @ResponseBody Forum addForum(@RequestBody Forum forum) {
        forumRepository.save(forum);
        return forum;
    }

    @GetMapping(value = "/forum/{id}")
    EntityModel<Forum> one(@PathVariable Integer id) {
        Forum forum = forumRepository.findById(id).orElseThrow(() -> new ForumNotFound(id));
        return EntityModel.of(forum, linkTo(methodOn(ForumController.class).one(id)).withSelfRel(),
                linkTo(methodOn(ForumController.class).getAllForums()).withRel("forums"));
    }

    @PutMapping(value = "/validateForum")
    public @ResponseBody Forum validateForum(@RequestBody Forum forum) {
        return forumRepository.findAllByForumTitle(forum.getForumTitle());
    }
}
