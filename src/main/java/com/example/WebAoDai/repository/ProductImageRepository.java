package com.example.WebAoDai.repository;

import com.example.WebAoDai.entity.Product;
import com.example.WebAoDai.entity.ProductImage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author HoangLH
 */
@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {


}
