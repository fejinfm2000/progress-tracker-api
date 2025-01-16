package com.fm.progresstracker.serviceImpl;


import com.fm.progresstracker.ExceptionHandler.NotFound;
import com.fm.progresstracker.dto.ActivityDto;
import com.fm.progresstracker.dto.CategoryDto;
import com.fm.progresstracker.dto.UserDto;
import com.fm.progresstracker.dto.VisitorDto;
import com.fm.progresstracker.entity.Activity;
import com.fm.progresstracker.entity.Category;
import com.fm.progresstracker.entity.User;
import com.fm.progresstracker.entity.Visitor;
import com.fm.progresstracker.mapper.CommonMapper;
import com.fm.progresstracker.repository.ActivityRepository;
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

    @Autowired
    ActivityRepository activityRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
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

    public UserDto isUserPersent(String email, String passwordHash) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new NotFound("User Not Found");
        } else if (!passwordHash.equals(optionalUser.get().getPasswordHash())) {
            throw new NotFound("InCorrect Password");
        }
        return CommonMapper.INSTENCE.toUserDto(optionalUser.get());
    }

    public CategoryDto addCatagory(CategoryDto categoryDto) {
        Category category = CommonMapper.INSTENCE.toCategory(categoryDto);
        return CommonMapper.INSTENCE.toCategoryDto(categoriesRepository.save(category));
    }

    public ActivityDto addActivity(ActivityDto activityDto) {
        Activity activity = CommonMapper.INSTENCE.toActivity(activityDto);
        return CommonMapper.INSTENCE.toActivityDto(activityRepository.save(activity));
    }

    public List<CategoryDto> addMultipleCatagory(List<CategoryDto> categoryDto) {
        List<Category> category = CommonMapper.INSTENCE.toCategory(categoryDto);
        return CommonMapper.INSTENCE.toCategoryDto(categoriesRepository.saveAll(category));
    }

}
