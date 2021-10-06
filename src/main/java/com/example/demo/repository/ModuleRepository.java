package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Module1;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModuleRepository extends JpaRepository<Module1, String> {
//        @Query(value = "select * from module1",nativeQuery = true)
//        List<Module1>  findAllModule1();
//
        Module1 findByModuleCode(String mcode);

}
