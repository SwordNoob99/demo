package com.example.demo.Project;

import com.example.demo.helpers.Generator;

import org.apache.tomcat.util.codec.binary.Base64;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import com.example.demo.helpers.Image;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.io.File;
import java.nio.file.Files;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@CrossOrigin(origins = "http://localhost:8082")
@RequestMapping(path = "/api/v1/Project")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(path = "/projects")
    public List<Project> project() {

        List<Project> projects = projectService.getProjects();
        FileUtils IoUtils = new FileUtils();
        List<Project> mappedProjets = projects.stream()
                .filter(Objects::nonNull)
                .map(project -> {
                    File file = new File("C:/pfe/images/" + project.getImgPath());
                    byte[] fileContent = new byte[0];

                    try {
                        fileContent = Files.readAllBytes(file.toPath());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    String encoded = Base64Utils.encodeToString(fileContent);
                    project.setImgPath(encoded);
                    return project;

                })
                .collect(Collectors.toList());

        return mappedProjets;

    }


    @RequestMapping(value = "/{ProjectId}", method = RequestMethod.GET)
    public Project projectById(@PathVariable @NotNull Long ProjectId) {


        Project project = projectService.getProjectById(ProjectId);

        File file = new File("C:/pfe/images/" + project.getImgPath());
        byte[] fileContent = new byte[0];

        try {
            fileContent = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String encoded = Base64Utils.encodeToString(fileContent);
        project.setImgPath(encoded);

        return project;


    }

    @RequestMapping(value = "/{ProjectId}", method = RequestMethod.DELETE)
    public void deleteProject(@PathVariable @NotNull Long ProjectId) {


        projectService.deleteProject(ProjectId);


    }


    @RequestMapping(
            value = "/project",
            method = RequestMethod.POST,
            consumes = {"application/json"})

    public void addProject(@RequestBody Map<String, Object> payload, @RequestHeader HttpHeaders headers)
            throws Exception {


        // CHECK IF IMAGE IS NOT EMPTY
        String imageValue = (String) payload.get("image");
        String imgPath = "";
        if (!imageValue.isEmpty()) {

            try {
                //This will decode the String which is encoded by using Base64 class

                // get extension
                String imgType = Image.extractMimeType(imageValue);
                imgType = imgType.split("/", -1)[1];
                String imageDataBytes = imageValue.substring(imageValue.indexOf(",") + 1);

                byte[] imageByte = Base64.decodeBase64(imageDataBytes);
                imgPath = Generator.GenerateRandomString() + "." + imgType;
                String directory = "C:/pfe/images/" + imgPath;

                new FileOutputStream(directory).write(imageByte);

                System.out.println("success");
            } catch (Exception e) {
                System.out.println("failed" + e.getMessage());
            }

        }

        // get body params

        String name = (String) payload.get("name");
        String description = (String) payload.get("description");

        // add project
        projectService.addNewProject(name, description, imgPath);


    }

    @RequestMapping(
            value = "/project",
            method = RequestMethod.PUT,
            consumes = {"application/json"})

    public void updateProject(@RequestBody Map<String, Object> payload, @RequestHeader HttpHeaders headers)
            throws Exception {


        // CHECK IF IMAGE IS NOT EMPTY
        String imageValue = (String) payload.get("image");
        String imgPath = "";
        if (!imageValue.isEmpty()) {

            try {
                //This will decode the String which is encoded by using Base64 class

                // get extension
                String imgType = Image.extractMimeType(imageValue);
                imgType = imgType.split("/", -1)[1];
                String imageDataBytes = imageValue.substring(imageValue.indexOf(",") + 1);

                byte[] imageByte = Base64.decodeBase64(imageDataBytes);
                imgPath = Generator.GenerateRandomString() + "." + imgType;
                String directory = "C:/pfe/images/" + imgPath;

                new FileOutputStream(directory).write(imageByte);

                System.out.println("success");
            } catch (Exception e) {
                System.out.println("failed" + e.getMessage());
            }

        }

        // get body params

        String name = (String) payload.get("name");
        String description = (String) payload.get("description");
        String Id = String.valueOf(payload.get("id"));
        Long  ConvertedId = Long.parseLong(Id);


        // add project
        projectService.UpdateProject(name , description , imgPath , ConvertedId);



    }


}
