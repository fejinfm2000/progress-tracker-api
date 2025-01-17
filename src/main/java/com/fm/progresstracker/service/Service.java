package com.fm.progresstracker.service;

import com.fm.progresstracker.dto.ActivityDto;
import com.fm.progresstracker.dto.ActivityRequestDto;
import com.fm.progresstracker.dto.CategoryDto;
import com.fm.progresstracker.dto.SubActivityDto;
import com.fm.progresstracker.dto.SubActivityRequestDto;
import com.fm.progresstracker.dto.UserDto;
import com.fm.progresstracker.dto.VisitorDto;
import com.fm.progresstracker.entity.User;

import java.util.List;


public interface Service {

    List<User> getAllUsers();

    UserDto addUser(UserDto userDto);

    VisitorDto addVisitor(VisitorDto visitor);

    List<VisitorDto> getVisitor();

    UserDto isUserPersent(String email, String passwordHash);

    CategoryDto addCatagory(CategoryDto catagoryDto);

    ActivityDto addActivity(ActivityRequestDto activity);

    SubActivityDto addSubActivity(SubActivityRequestDto activity);

    List<CategoryDto> addMultipleCatagory(List<CategoryDto> catagoryDto);


}
