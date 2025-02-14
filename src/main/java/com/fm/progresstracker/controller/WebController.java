package com.fm.progresstracker.controller;

import com.fm.progresstracker.dto.ActivityDto;
import com.fm.progresstracker.dto.ActivityRequestDto;
import com.fm.progresstracker.dto.CategoryDto;
import com.fm.progresstracker.dto.NewsDto;
import com.fm.progresstracker.dto.SubActivityDto;
import com.fm.progresstracker.dto.SubActivityRequestDto;
import com.fm.progresstracker.dto.UserActivityResponseDto;
import com.fm.progresstracker.dto.UserDto;
import com.fm.progresstracker.dto.VisitorDto;
import com.fm.progresstracker.entity.User;
import com.fm.progresstracker.service.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/isUserPersent")
    public ResponseEntity<UserDto> isUserPersent(@RequestParam String email, @RequestParam String passwordHash) {
        log.info("isUserPersent");
        UserDto userDto = Service.isUserPersent(email, passwordHash);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PatchMapping("/addCatagory")
    public ResponseEntity<CategoryDto> addCatagory(@RequestBody CategoryDto Category) {
        log.info("addCatagory");
        CategoryDto catagoryResponse = Service.addCatagory(Category);
        return new ResponseEntity<>(catagoryResponse, HttpStatus.OK);
    }

    @PatchMapping("/addMultipleCatagory")
    public ResponseEntity<List<CategoryDto>> addMultipleCatagory(@RequestBody List<CategoryDto> Category) {
        log.info("addMultipleCatagory");
        List<CategoryDto> catagoryResponse = Service.addMultipleCatagory(Category);
        return new ResponseEntity<>(catagoryResponse, HttpStatus.OK);
    }

    @GetMapping("/getTodayNews")
    public ResponseEntity<NewsDto> getTodayNews() {
        log.info("getTodayNews");
        NewsDto newsDtos = Service.getTodayNews();
        return new ResponseEntity<>(newsDtos, HttpStatus.OK);
    }

    @GetMapping("/getAllCatagories")
    public ResponseEntity<List<CategoryDto>> getAllCatagory() {
        log.info("getAllCatagory");
        List<CategoryDto> categoryDtos = Service.getAllCatagory();
        return new ResponseEntity<>(categoryDtos, HttpStatus.OK);
    }

    /*
    User Specific
     */

    @GetMapping("/getAllActivities")
    public ResponseEntity<UserActivityResponseDto> getAllActivities(@RequestParam String userEmail) {
        log.info("getAllActivities");
        UserActivityResponseDto userActivityResponse = Service.getAllActivities(userEmail);
        return new ResponseEntity<>(userActivityResponse, HttpStatus.OK);
    }

    @PatchMapping("/addActivity")
    public ResponseEntity<ActivityDto> addActivity(@RequestBody ActivityRequestDto activity) {
        log.info("addActivity");
        ActivityDto activityDto = Service.addActivity(activity);
        return new ResponseEntity<>(activityDto, HttpStatus.OK);
    }

    @PatchMapping("/addSubActivity")
    public ResponseEntity<SubActivityDto> addSubActivity(@RequestBody SubActivityRequestDto subActivity) {
        log.info("addSubActivity");
        SubActivityDto activityDto = Service.addSubActivity(subActivity);
        return new ResponseEntity<>(activityDto, HttpStatus.OK);
    }

    @PatchMapping("/addActivityDetails")
    public ResponseEntity<UserActivityResponseDto> addActivityDetails(@RequestBody ActivityRequestDto activityRequestDto) {
        log.info("addActivityDetails");
        UserActivityResponseDto activity = Service.addActivityDetails(activityRequestDto);
        return new ResponseEntity<>(activity, HttpStatus.OK);
    }

    @GetMapping("/getSubActivity/{activityId}")
    public ResponseEntity<List<SubActivityDto>> getSubActivity(@PathVariable Integer activityId, @RequestParam String email) {
        log.info("getSubActivity");
        List<SubActivityDto> subActivityDtos = Service.getSubActivity(activityId, email);
        return new ResponseEntity<>(subActivityDtos, HttpStatus.OK);
    }

    @PatchMapping("/patchSubActivity/{activityId}")
    public ResponseEntity<SubActivityDto> patchSubActivity(@PathVariable Integer activityId, @RequestBody SubActivityDto subActivityDto) {
        log.info("patchSubActivity");
        SubActivityDto subActivityDtos = Service.patchSubActivity(activityId, subActivityDto);
        return new ResponseEntity<>(subActivityDtos, HttpStatus.OK);
    }

}
