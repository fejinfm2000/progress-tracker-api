package com.fm.progresstracker.serviceImpl;


import com.fm.progresstracker.ExceptionHandler.NotFound;
import com.fm.progresstracker.dto.UserDto;
import com.fm.progresstracker.dto.VisitorDto;
import com.fm.progresstracker.entity.User;
import com.fm.progresstracker.entity.Visitor;
import com.fm.progresstracker.mapper.CommonMapper;
import com.fm.progresstracker.repository.CategoriesRepository;
import com.fm.progresstracker.repository.UserRepository;
import com.fm.progresstracker.repository.VisitorRepository;
import com.fm.progresstracker.service.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceImplementation implements Service {

    @Autowired
    CategoriesRepository categoriesRepository;

    @Autowired
    VisitorRepository visitorRepository;

    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return categoriesRepository.findAll();
    }

    public VisitorDto addVisitor(VisitorDto visitorDto) {
        Visitor visitor = CommonMapper.INSTENCE.toViositorEntity(visitorDto);
        return CommonMapper.INSTENCE.toViositorDto(visitorRepository.save(visitor));
    }

    public UserDto addUser(UserDto userDto) {
        User user = CommonMapper.INSTENCE.toUser(userDto);
        return CommonMapper.INSTENCE.toUserDto(userRepository.save(user));
    }

    public List<VisitorDto> getVisitor() {
        return CommonMapper.INSTENCE.toViositorList(visitorRepository.findAll());
    }

    public UserDto isUserPersent(UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
        if (optionalUser.isEmpty()) {
            throw new NotFound("User Not Found");
        } else if (!userDto.getPasswordHash().equals(optionalUser.get().getPasswordHash())) {
            throw new NotFound("InCorrect Password");
        }
        return CommonMapper.INSTENCE.toUserDto(optionalUser.get());
    }


}
