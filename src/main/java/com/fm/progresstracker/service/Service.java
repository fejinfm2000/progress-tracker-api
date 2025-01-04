package com.fm.progresstracker.service;

import com.fm.progresstracker.dto.VisitorDto;
import com.fm.progresstracker.entity.User;

import java.util.List;


public interface Service {

    List<User> getAllUsers();
    VisitorDto addVisitor(VisitorDto visitor);
    List<VisitorDto> getVisitor();

}
