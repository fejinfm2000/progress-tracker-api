package com.fm.progresstracker.repository;

import com.fm.progresstracker.entity.SubActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubActivityRepository extends JpaRepository<SubActivity, Integer> {

    List<SubActivity> findByActivity_ActivityNameIn(List<String> activityId);
}
