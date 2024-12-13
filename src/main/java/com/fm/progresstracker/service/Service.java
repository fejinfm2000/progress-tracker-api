package com.fm.progresstracker.service;

import com.fm.progresstracker.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


public interface Service {

    public List<User> getAllUsers();

}
