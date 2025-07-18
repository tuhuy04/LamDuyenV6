package com.example.WebAoDai.repository;

import com.example.WebAoDai.entity.CategorySize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategorySizeRepository extends JpaRepository<CategorySize,Integer>{

    List<CategorySize> findAllByCategory_Id(Integer id);

}
