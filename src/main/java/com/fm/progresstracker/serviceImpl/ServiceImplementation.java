package com.fm.progresstracker.serviceImpl;


import com.fm.progresstracker.ExceptionHandler.NotFound;
import com.fm.progresstracker.dto.ActivityDto;
import com.fm.progresstracker.dto.ActivityRequestDto;
import com.fm.progresstracker.dto.CategoryDto;
import com.fm.progresstracker.dto.NewsDto;
import com.fm.progresstracker.dto.NewsDtoResponse;
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
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    @Autowired
    RestTemplate restTemplate;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public VisitorDto addVisitor(VisitorDto visitorDto) {
        Visitor visitor = CommonMapper.INSTENCE.toViositorEntity(visitorDto);
        return CommonMapper.INSTENCE.toViositorDto(visitorRepository.save(visitor));
    }

    public UserDto addUser(UserDto userDto) {
        User user = CommonMapper.INSTENCE.toUser(userDto);
        if (userDto.getUpdateFlag() != null) {
            Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
            if (optionalUser.isEmpty()) {
                throw new NotFound("User Not Found");
            }
            user = userRepository.findByEmail(userDto.getEmail()).get();
            user.setPasswordHash(userDto.getPasswordHash());
        }
        return CommonMapper.INSTENCE.toUserDto(userRepository.save(user));
    }

    public List<VisitorDto> getVisitor() {
        return CommonMapper.INSTENCE.toViositorList(visitorRepository.findAll());
    }

    public List<SubActivityDto> getSubActivity(Integer activityId, String email) {
        return CommonMapper.INSTENCE.toSubActivityDtoList(subActivityRepository.findByActivity_ActivityIdAndActivity_User_Email(activityId, email));
    }

    public SubActivityDto patchSubActivity(Integer activityId, SubActivityDto subActivityDto) {
        SubActivity subActivity = subActivityRepository.findBySubActivityIdAndActivity_ActivityIdAndActivity_User_Email(subActivityDto.getSubActivityId(), activityId, subActivityDto.getEmail());
        subActivity.setStatus(subActivityDto.getStatus());
        subActivity.setProgress(subActivityDto.getProgress());
        if ("Completed".equals(subActivity.getStatus())) {
            subActivity.setEndDate(LocalDate.now());
        }
        SubActivity subActivityResponse = subActivityRepository.save(subActivity);
        return CommonMapper.INSTENCE.toSubActivityDto(subActivityResponse);
    }

    public UserDto isUserPersent(String email, String passwordHash) {
        User user = findByUserEmail(email);
        if (user.getEmail() == null) {
            throw new NotFound("User Not Found");
        } else if (!passwordHash.equals(user.getPasswordHash())) {
            throw new NotFound("InCorrect Password");
        }
        return CommonMapper.INSTENCE.toUserDto(user);
    }

    public CategoryDto addCatagory(CategoryDto categoryDto) {
        Category category = CommonMapper.INSTENCE.toCategory(categoryDto);
        return CommonMapper.INSTENCE.toCategoryDto(categoriesRepository.save(category));
    }

    public ActivityDto addActivity(ActivityRequestDto activityDto) {
        Activity activity = activityRepository.findByActivityNameAndUser_Email(activityDto.getActivityName(), activityDto.getEmail());
        if (activity == null) {
            Category category = categoriesRepository.findByCategoryName(activityDto.getCategoryName());
            User user = findByUserEmail(activityDto.getEmail());
            activity = Activity.builder()
                    .activityId(activityDto.getActivityId())
                    .activityName(activityDto.getActivityName())
                    .progress(activityDto.getProgress())
                    .endDate(activityDto.getEndDate())
                    .startDate(LocalDate.now())
                    .status(activityDto.getStatus())
                    .description(activityDto.getDescription())
                    .category(category)
                    .user(user)
                    .build();
            activityRepository.save(activity);
        }
        return CommonMapper.INSTENCE.toActivityDto(activity);
    }

    public SubActivityDto addSubActivity(SubActivityRequestDto activityDto) {
        SubActivity subActivity = subActivityRepository.findBySubActivityIdAndActivity_ActivityNameAndActivity_User_Email(activityDto.getSubActivityId(), activityDto.getActivityName(), activityDto.getEmail());
        if (subActivity == null) {
            Activity activity = activityRepository.findByActivityNameAndUser_Email(activityDto.getActivityName(), activityDto.getEmail());
            if (activity == null) {
                throw new NotFound("Activity Not available");
            }
            subActivity = SubActivity.builder()
                    .description(activityDto.getDescription())
                    .subActivityName(activityDto.getSubActivityName())
                    .progress(activityDto.getProgress())
                    .endDate(activityDto.getEndDate())
                    .startDate(activityDto.getStartDate() != null ? activityDto.getStartDate() : LocalDate.now())
                    .status(activityDto.getStatus())
                    .activity(activity)
                    .build();
            subActivityRepository.save(subActivity);
        }

        return CommonMapper.INSTENCE.toSubActivityDto(subActivity);
    }

    public UserActivityResponseDto getAllActivities(String userEmail) {
        User user = findByUserEmail(userEmail);

        List<Activity> activity = activityRepository.findByUser_UserId(user.getUserId());

        List<SubActivity> subActivity = subActivityRepository.findByActivity_ActivityNameInAndActivity_User_Email(activity.stream().map(Activity::getActivityName).toList(), userEmail);
        List<Category> categories = categoriesRepository.findByCategoryNameIn(activity.stream().map(data -> data.getCategory().getCategoryName()).toList());

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


    public UserActivityResponseDto addActivityDetails(ActivityRequestDto activityRequestDto) {
        ActivityDto activityDto = addActivity(activityRequestDto);
        activityRequestDto.getSubActivities().forEach(data -> {
            SubActivityRequestDto subActivity = SubActivityRequestDto.builder()
                    .activityName(activityDto.getActivityName())
                    .description(data.getDescription())
                    .subActivityName(data.getSubActivityName())
                    .subActivityId(data.getSubActivityId())
                    .status(data.getStatus())
                    .email(activityRequestDto.getEmail())
                    .progress(data.getProgress() != null ? data.getProgress() : BigDecimal.ZERO)
                    .startDate(activityRequestDto.getEndDate() != null ? activityRequestDto.getEndDate() : LocalDate.now())
                    .endDate(activityRequestDto.getEndDate())
                    .build();
            addSubActivity(subActivity);
        });

        return getAllActivities(activityRequestDto.getEmail());
    }

    User findByUserEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElseGet(() -> User.builder().build());
    }

    public List<CategoryDto> addMultipleCatagory(List<CategoryDto> categoryDto) {
        List<Category> category = CommonMapper.INSTENCE.toCategory(categoryDto);
        return CommonMapper.INSTENCE.toCategoryDto(categoriesRepository.saveAll(category));
    }

    public List<CategoryDto> getAllCatagory() {
        return CommonMapper.INSTENCE.toCategoryDto(categoriesRepository.findAll());
    }

    public NewsDto getTodayNews() {
        String url = "http://api.mediastack.com/v1/news?access_key=6f2984d27b6577e16fe4bbd5a2acc3c0";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<NewsDtoResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<>() {
                }
        );
        return CommonMapper.INSTENCE.toNewsDto(response.getBody());
    }
}
