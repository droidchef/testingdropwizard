package me.negotiatewith.app.core.dto.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.dropwizard.jackson.JsonSnakeCase;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.negotiatewith.app.db.model.entity.Profile;
import me.negotiatewith.app.db.model.entity.User;
import org.joda.time.DateTime;


@Data
@NoArgsConstructor
@JsonSnakeCase
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private DateTime createdAt;
    private DateTime updatedAt;

    private ProfileDto profile = null;

    public UserDto(User user, boolean withProfile) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();

        if(withProfile)
            this.profile = new ProfileDto(user.getProfile());
    }

}
