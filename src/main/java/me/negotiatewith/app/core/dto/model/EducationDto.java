package me.negotiatewith.app.core.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.dropwizard.jackson.JsonSnakeCase;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.negotiatewith.app.db.model.entity.Education;
import org.joda.time.DateTime;

import java.sql.Date;

@Data
@NoArgsConstructor
@JsonSnakeCase
@JsonIgnoreProperties(ignoreUnknown = true)
public class EducationDto {

    private String level;
    private String time;
    private String school;
    private String university;

    public EducationDto(Education education) {
        this.level = education.getLevel();
        this.time = education.getTime();
        this.school = education.getSchool();
        this.university = education.getUniversity();
    }
}
