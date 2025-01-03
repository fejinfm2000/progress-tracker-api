package com.fm.progresstracker.repository;

import com.fm.progresstracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<User, Integer> {
}
