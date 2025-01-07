package com.fm.progresstracker.controller;

import com.fm.progresstracker.dto.UserDto;
import com.fm.progresstracker.dto.VisitorDto;
import com.fm.progresstracker.entity.User;
import com.fm.progresstracker.service.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.List;


@Slf4j
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
        log.info("getAllUsers");
        List<User> user = Service.getAllUsers();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/addUser")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        log.info("addUser");
        UserDto user = Service.addUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/addVisitor")
    public ResponseEntity<VisitorDto> addVisitor(@RequestBody VisitorDto visitorDto) {
        log.info("addVisitor");
        VisitorDto visitorResponse = Service.addVisitor(visitorDto);
        return new ResponseEntity<>(visitorResponse, HttpStatus.OK);
    }

    @GetMapping("/getAllVisitor")
    public ResponseEntity<List<VisitorDto>> getVisitor() {
        log.info("getVisitor");
        List<VisitorDto> visitorList = Service.getVisitor();
        return new ResponseEntity<>(visitorList, HttpStatus.OK);
    }

}
