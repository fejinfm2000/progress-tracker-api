package com.fm.progresstracker.repository;

import com.fm.progresstracker.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, Integer> {
    Category findByCategoryName(String categoryName);

    List<Category> findByCategoryNameIn(List<String> categoryName);
}
