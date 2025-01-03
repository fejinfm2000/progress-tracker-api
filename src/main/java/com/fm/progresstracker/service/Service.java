package com.fm.progresstracker.service;

import com.fm.progresstracker.entity.User;
import com.fm.progresstracker.entity.Visitor;

import java.util.List;


public interface Service {

    List<User> getAllUsers();
    Visitor addVisitor(Visitor visitor);

}
