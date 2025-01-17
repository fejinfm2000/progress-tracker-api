package com.fm.progresstracker.repository;

import com.fm.progresstracker.entity.SubActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubActivityRepository extends JpaRepository<SubActivity, Integer> {

}
