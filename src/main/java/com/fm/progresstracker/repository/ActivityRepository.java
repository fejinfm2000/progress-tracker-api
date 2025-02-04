package com.fm.progresstracker.repository;

import com.fm.progresstracker.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {

    Activity findByActivityNameAndUser_Email(String activityName, String email);

    List<Activity> findByUser_UserId(Integer userId);

}
