package com.fm.progresstracker.serviceImpl;


import com.fm.progresstracker.entity.User;
import com.fm.progresstracker.repository.CategoriesRepository;
import com.fm.progresstracker.repository.VisitorRepository;
import com.fm.progresstracker.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fm.progresstracker.entity.Visitor;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceImplementation implements Service {

    @Autowired
    CategoriesRepository categoriesRepository;

    @Autowired
    VisitorRepository visitorRepository;

    public List<User> getAllUsers() {
        return categoriesRepository.findAll();
    }

    public Visitor addVisitor(Visitor visitor) {
        return visitorRepository.save(visitor);
    }

}
