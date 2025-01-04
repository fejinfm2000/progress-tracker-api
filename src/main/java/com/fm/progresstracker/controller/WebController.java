package com.fm.progresstracker.controller;

import com.fm.progresstracker.dto.VisitorDto;
import com.fm.progresstracker.entity.User;
import com.fm.progresstracker.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.List;


@RestController
@RequestMapping("/api/tracker")
public class WebController {
    @Autowired
    Service Service;

    @GetMapping
    public String aliveCheck() {
        System.out.println("Am I Live.");
        return "I am Alive";
    }

    @GetMapping("/allUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> user = Service.getAllUsers();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/addVisitor")
    public ResponseEntity<VisitorDto> addVisitor(@RequestBody VisitorDto visitorDto) {
        VisitorDto visitorResponse = Service.addVisitor(visitorDto);
        return new ResponseEntity<>(visitorResponse, HttpStatus.OK);
    }

    @GetMapping("/getAllVisitor")
    public ResponseEntity<List<VisitorDto>> getVisitor() {
        List<VisitorDto> visitorList = Service.getVisitor();
        return new ResponseEntity<>(visitorList, HttpStatus.OK);
    }

}
