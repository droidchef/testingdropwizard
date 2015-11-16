package me.negotiatewith.app.db.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.negotiatewith.app.core.utils.JodaDateTimeConverter;
import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * Created by ishan on 16/11/15.
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "profiles")
public class Profile extends BaseEntity {

    private static final long serialVersionUID = -7908033907899412275L;

    @Column(name = "is_seeker")
    private Boolean isSeeker;

    @Column(name = "is_hunter")
    private Boolean isHunter;

    @Column(name = "date_of_birth")
    @Convert(converter = JodaDateTimeConverter.class)
    private DateTime dateOfBirth;

}

