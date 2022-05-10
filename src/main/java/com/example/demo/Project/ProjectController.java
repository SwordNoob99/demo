package com.example.demo.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
@RequestMapping(path = "/api/v1/Project")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<Project> project(){

        return projectService.getStudents();

    }


    @RequestMapping(
            value = "/project",
            method = RequestMethod.POST,
            consumes = {"application/json"})

    public void addProject(@RequestBody HttpServletRequest request)
            throws Exception{

        System.out.println(request.getParameter("name"));
        /*projectService.addNewProject(project);*/

    }


}
