package com.example.demo.Project;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getProjects(){

        return projectRepository.findAll();



    }

    public Project getProjectById(Long Id){

        return projectRepository.getById(Id);
    }

    public void UpdateProject(String name, String description , String ImgPath , Long Id){

         projectRepository.updateProjectInfo(name , description , ImgPath , Id);
    }



    public void addNewProject(String name , String description , String imgPath){

            projectRepository.save(new Project(name , description , imgPath));

    }

    public void deleteProject(Long Id){

        projectRepository.deleteById(Id);

    }
}
