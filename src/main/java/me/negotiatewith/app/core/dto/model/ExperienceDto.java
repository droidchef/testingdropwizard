package me.negotiatewith.app.core.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.dropwizard.jackson.JsonSnakeCase;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.negotiatewith.app.db.model.entity.Experience;

@Data
@NoArgsConstructor
@JsonSnakeCase
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExperienceDto {

    private String role;
    private String organisation;
    private Integer months;

    public ExperienceDto(Experience experience) {
        this.role = experience.getRole();
        this.organisation = experience.getOrganisation();
        this.months = experience.getMonths();
    }
}
