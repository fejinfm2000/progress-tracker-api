package com.fm.progresstracker.serviceImpl;


import com.fm.progresstracker.dto.VisitorDto;
import com.fm.progresstracker.entity.User;
import com.fm.progresstracker.mapper.CommonMapper;
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

    public VisitorDto addVisitor(VisitorDto visitorDto) {
    Visitor visitor= CommonMapper.INSTENCE.toViositorEntity(visitorDto);
        return CommonMapper.INSTENCE.toViositorDto(visitorRepository.save(visitor));
    }
    public List<VisitorDto> getVisitor() {
        return CommonMapper.INSTENCE.toViositorList(visitorRepository.findAll());
    }


}
