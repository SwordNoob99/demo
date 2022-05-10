package com.example.demo.Project;


import javax.persistence.*;

@Entity
@Table
public class Project {

    @Id
    @SequenceGenerator(
            name = "project_sequence",
            sequenceName = "project_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "project_sequence"
    )

    private long Id ;
    private String Name;
    private String Description;
    private String ImgPath;

    public Project() {
    }

    public Project(long id, String name, String description, String imgPath) {
        Id = id;
        Name = name;
        Description = description;
        ImgPath = imgPath;
    }

    public Project(String name, String description, String imgPath) {
        Name = name;
        Description = description;
        ImgPath = imgPath;
    }

    @Override
    public String toString() {
        return "Project{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Description='" + Description + '\'' +
                ", ImgPath='" + ImgPath + '\'' +
                '}';
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImgPath() {
        return ImgPath;
    }

    public void setImgPath(String imgPath) {
        ImgPath = imgPath;
    }
}
