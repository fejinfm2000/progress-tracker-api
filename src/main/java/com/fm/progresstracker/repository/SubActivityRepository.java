package com.fm.progresstracker.repository;

import com.fm.progresstracker.entity.SubActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubActivityRepository extends JpaRepository<SubActivity, Integer> {

    List<SubActivity> findByActivity_ActivityNameInAndActivity_User_Email(List<String> activityId, String email);

    List<SubActivity> findByActivity_ActivityIdAndActivity_User_Email(Integer activityId, String email);

    SubActivity findBySubActivityIdAndActivity_ActivityIdAndActivity_User_Email(Integer SubActId, Integer activityId, String email);

    SubActivity findBySubActivityIdAndActivity_ActivityNameAndActivity_User_Email(Integer subActivityId, String activityName, String email);
}
