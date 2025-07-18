package com.example.WebAoDai.repository;

import com.example.WebAoDai.entity.Product;
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
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p LEFT JOIN Category c ON p.category.id = c.id WHERE p.isActive = 1 and c.status = 1 and p.quantity > 0 and " +
            "(:search IS NULL AND :search IS NULL) OR " +
            "(LOWER(p.product_Name) LIKE LOWER(CONCAT('%', :search, '%')) AND :search IS NOT NULL) OR " +
            "(LOWER(c.name) LIKE LOWER(CONCAT('%', :search, '%')) AND :search IS NOT NULL)")
    List<Product> findByProductNameOrCategoryName(@Param("search") String search);

    List<Product> findAllByIsActive(Integer isActive);

    @Query(value = "select * from product p where p.product_name like %?1% and p.is_active = 1", nativeQuery = true)
    List<Product> findByProduct_NameContaining(String name);

    @Query(value = "SELECT p.* FROM Product p where p.is_active = 1 GROUP BY p.id ORDER BY MAX(p.quantity) DESC LIMIT 12", nativeQuery = true)
    List<Product> findTop12ProductBestSellers();

    @Query(value = "Select * From product p where p.is_active = 1 ORDER BY p.created_at DESC LIMIT 12;", nativeQuery = true)
    List<Product> findTop12ProductNewArrivals();


    @Query(value = "select * from `fashionstore`.product p where p.product_name like %?1% and p.category_id= ?2 and p.is_selling = 1", nativeQuery = true)
    Page<Product> findByProduct_NameAndCategory_idContaining(String name, int category_id, Pageable pageable);

    @Query(value = "select * from `fashionstore`.product p where  p.product_name like %?1% and p.is_selling = 1", nativeQuery = true)
    Page<Product> findByProduct_NameContaining(String name, Pageable pageable);

    @Query(value = "select * from product p where p.is_active = 1 and p.category_id = ?1 ORDER BY p.sold DESC LIMIT 4;", nativeQuery = true)
    List<Product> findTop4ProductByCategory_id(int id);

    @Query("SELECT p FROM Product p WHERE p.isActive = 1 AND p.gender = :gender")
    List<Product> findByGender(@Param("gender") String gender);

    Page<Product> findAllByIsActive(Integer isActive, Pageable pageable);
}
