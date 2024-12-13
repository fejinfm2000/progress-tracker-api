package com.fm.progresstracker.controller;

import com.fm.progresstracker.entity.User;
import com.fm.progresstracker.serviceImpl.ServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/tracker")
public class WebController {
    @Autowired
    ServiceImplementation ServiceImplementation;

    @GetMapping
    public String aliveCheck() {
        return "I am Alive";
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> user = ServiceImplementation.getAllUsers();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
