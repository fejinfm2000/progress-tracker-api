package com.fm.progresstracker.mapper;

import com.fm.progresstracker.dto.ActivityDto;
import com.fm.progresstracker.dto.ActivityRequestDto;
import com.fm.progresstracker.dto.CategoryDto;
import com.fm.progresstracker.dto.NewsDto;
import com.fm.progresstracker.dto.NewsDtoResponse;
import com.fm.progresstracker.dto.SubActivityDto;
import com.fm.progresstracker.dto.UserDto;
import com.fm.progresstracker.dto.VisitorDto;
import com.fm.progresstracker.entity.Activity;
import com.fm.progresstracker.entity.Category;
import com.fm.progresstracker.entity.SubActivity;
import com.fm.progresstracker.entity.User;
import com.fm.progresstracker.entity.Visitor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper()
public interface CommonMapper {
    CommonMapper INSTENCE = Mappers.getMapper(CommonMapper.class);

    Visitor toViositorEntity(VisitorDto visitorDto);

    VisitorDto toViositorDto(Visitor visitor);

    List<VisitorDto> toViositorList(List<Visitor> visitorList);

    User toUser(UserDto userDto);

    UserDto toUserDto(User user);

    Activity toActivity(ActivityDto activityDto);

    ActivityDto toActivityDto(Activity activity);

    List<ActivityDto> toActivityDtoList(List<Activity> activity);

    SubActivityDto toSubActivityDto(SubActivity activity);

    Activity toActivity(ActivityRequestDto activityRequestDto);

    List<SubActivityDto> toSubActivityDtoList(List<SubActivity> activity);

    Category toCategory(CategoryDto categoryDto);

    List<Category> toCategory(List<CategoryDto> categoryDto);

    CategoryDto toCategoryDto(Category category);

    List<CategoryDto> toCategoryDto(List<Category> category);

    NewsDto toNewsDto(NewsDtoResponse newsDtoResponse);

}
