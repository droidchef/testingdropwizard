package me.negotiatewith.app.core.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.dropwizard.jackson.JsonSnakeCase;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.negotiatewith.app.db.model.entity.Education;
import me.negotiatewith.app.db.model.entity.Experience;
import me.negotiatewith.app.db.model.entity.Resume;
import org.joda.time.DateTime;

import java.lang.reflect.Type;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@JsonSnakeCase
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResumeDto {

    private Long currentCtc;
    private List<String> skills;
    private List<ExperienceDto> experienceList;
    private List<EducationDto> education;

    public ResumeDto(Resume resume) {
        this.currentCtc = resume.getCurrentCtc();
        Gson gson = new Gson();
        Type listTypeSkills = new TypeToken<List<String>>() {}.getType();
        this.skills = gson.fromJson(resume.getSkills(), listTypeSkills);

        Type listTypeExperience = new TypeToken<List<Experience>>() {}.getType();
        System.out.println(resume.getExperiences());
        List<Experience> experiences = gson.fromJson(resume.getExperiences(), listTypeExperience);
        this.experienceList = new ArrayList<>();
        this.experienceList.addAll(experiences.stream().map(ExperienceDto::new).collect(Collectors.toList()));

        Type listTypeEducation = new TypeToken<List<Education>>() {}.getType();
        List<Education> educationList = gson.fromJson(resume.getEducation(), listTypeEducation);
        this.education = new ArrayList<>();
        this.education.addAll(educationList.stream().map(EducationDto::new).collect(Collectors.toList()));

    }

}
