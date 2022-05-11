package com.example.demo.Project;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
public interface  ProjectRepository  extends JpaRepository<Project , Long> {

    @Transactional
    @Modifying

    @Query("update Project p set p.Name = ?1 , p.Description = ?2 , p.ImgPath = ?3 where p.id = ?4")
    void updateProjectInfo(String name, String description , String ImgPath , Long Id);
}
