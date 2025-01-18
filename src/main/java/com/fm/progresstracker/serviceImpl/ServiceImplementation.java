package com.fm.progresstracker.serviceImpl;


import com.fm.progresstracker.ExceptionHandler.NotFound;
import com.fm.progresstracker.dto.ActivityDto;
import com.fm.progresstracker.dto.ActivityRequestDto;
import com.fm.progresstracker.dto.CategoryDto;
import com.fm.progresstracker.dto.SubActivityDto;
import com.fm.progresstracker.dto.SubActivityRequestDto;
import com.fm.progresstracker.dto.UserActivityResponseDto;
import com.fm.progresstracker.dto.UserDto;
import com.fm.progresstracker.dto.VisitorDto;
import com.fm.progresstracker.entity.Activity;
import com.fm.progresstracker.entity.Category;
import com.fm.progresstracker.entity.SubActivity;
import com.fm.progresstracker.entity.User;
import com.fm.progresstracker.entity.Visitor;
import com.fm.progresstracker.mapper.CommonMapper;
import com.fm.progresstracker.repository.ActivityRepository;
import com.fm.progresstracker.repository.CategoriesRepository;
import com.fm.progresstracker.repository.SubActivityRepository;
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

    @Autowired
    SubActivityRepository subActivityRepository;

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

    public ActivityDto addActivity(ActivityRequestDto activityDto) {
        System.out.println(activityDto.toString());
        Category category = categoriesRepository.findByCategoryName(activityDto.getCategoryName());
        System.out.println(category);
        Optional<User> user = userRepository.findByEmail(activityDto.getEmail());
        System.out.println(user.get().toString());
        Activity activity = Activity.builder()
                .activityId(activityDto.getActivityId())
                .activityName(activityDto.getActivityName())
                .progress(activityDto.getProgress())
                .endDate(activityDto.getEndDate())
                .startDate(activityDto.getStartDate())
                .status(activityDto.getStatus())
                .description(activityDto.getDescription())
                .category(category)
                .user(user.get())
                .build();
        activityRepository.save(activity);
        return CommonMapper.INSTENCE.toActivityDto(activity);
    }

    public SubActivityDto addSubActivity(SubActivityRequestDto activityDto) {
        Activity activity = activityRepository.findByActivityName(activityDto.getActivityName());
        SubActivity subActivity = SubActivity.builder()
                .description(activityDto.getDescription())
                .subActivityName(activityDto.getSubActivityName())
                .progress(activityDto.getProgress())
                .endDate(activityDto.getEndDate())
                .startDate(activityDto.getStartDate())
                .status(activityDto.getStatus())
                .activity(activity)
                .build();
        subActivityRepository.save(subActivity);
        return CommonMapper.INSTENCE.toSubActivityDto(subActivity);
    }

    public UserActivityResponseDto getAllActivities(String userEmail) {
        Optional<User> optionalUser = userRepository.findByEmail(userEmail);
        User user = optionalUser.get();

        List<Activity> activity = activityRepository.findByUser_UserId(user.getUserId());
        System.out.println("activity:::" + activity);

        List<SubActivity> subActivity = subActivityRepository.findByActivity_ActivityNameIn(activity.stream().map(Activity::getActivityName).toList());
        System.out.println("subActivity:::" + subActivity);
        List<Category> categories = categoriesRepository.findByCategoryNameIn(activity.stream().map(data -> data.getCategory().getCategoryName()).toList());
        System.out.println("categories:::" + categories);

        return UserActivityResponseDto.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .lastLogin(user.getLastLogin())
                .termsAndConditionFlag(user.getTermsAndConditionFlag())
                .subActivity(CommonMapper.INSTENCE.toSubActivityDtoList(subActivity))
                .activity(CommonMapper.INSTENCE.toActivityDtoList(activity))
                .category(CommonMapper.INSTENCE.toCategoryDto(categories))
                .build();
    }

    public List<CategoryDto> addMultipleCatagory(List<CategoryDto> categoryDto) {
        List<Category> category = CommonMapper.INSTENCE.toCategory(categoryDto);
        return CommonMapper.INSTENCE.toCategoryDto(categoriesRepository.saveAll(category));
    }

}
