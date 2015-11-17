package me.negotiatewith.app.core.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.dropwizard.jackson.JsonSnakeCase;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.negotiatewith.app.core.utils.WorthCalculator;
import me.negotiatewith.app.db.model.entity.Profile;
import org.joda.time.DateTime;

import java.sql.Date;

@Data
@NoArgsConstructor
@JsonSnakeCase
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfileDto {

    private Long id;
    private Boolean isSeeker;
    private Boolean isHunter;
    private Date dateOfBirth;
    private Double currentWorth;
    private ResumeDto resume;

    public ProfileDto(Profile profile) {
        this.id = profile.getId();
        this.isSeeker = profile.getIsSeeker();
        this.isHunter = profile.getIsHunter();
        this.dateOfBirth = profile.getDateOfBirth();

        this.resume = new ResumeDto(profile.getResume());

        currentWorth = WorthCalculator.getWorthOfSkills(resume.getSkills(), 2200000);

    }
}
