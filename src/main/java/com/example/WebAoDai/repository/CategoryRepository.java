package com.example.WebAoDai.repository;

import com.example.WebAoDai.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category getById(int id);

    Optional<Category> findByName(String name);

    List<Category> findAllByStatus(Integer status);

}
