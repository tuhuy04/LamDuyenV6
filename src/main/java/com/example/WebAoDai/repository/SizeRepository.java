package com.example.WebAoDai.repository;

import com.example.WebAoDai.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SizeRepository extends JpaRepository<Size,Integer>{
}
