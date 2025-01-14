package com.fm.progresstracker.service;

import com.fm.progresstracker.dto.UserDto;
import com.fm.progresstracker.dto.VisitorDto;
import com.fm.progresstracker.entity.User;

import java.util.List;


public interface Service {

    List<User> getAllUsers();

    UserDto addUser(UserDto userDto);

    VisitorDto addVisitor(VisitorDto visitor);

    List<VisitorDto> getVisitor();

    UserDto isUserPersent(String email,String passwordHash);

}
