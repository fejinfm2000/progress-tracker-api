package com.fm.progresstracker.serviceImpl;


import com.fm.progresstracker.entity.User;
import com.fm.progresstracker.repository.CategoriesRepository;
import com.fm.progresstracker.service.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceImplementation implements Service {

    @Autowired
    CategoriesRepository categoriesRepository;

    public List<User> getAllUsers() {
        return categoriesRepository.findAll();
    }

}
