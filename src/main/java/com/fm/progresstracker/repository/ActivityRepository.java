package com.fm.progresstracker.repository;

import com.fm.progresstracker.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {

    Activity findByActivityName(String activityName);

}
