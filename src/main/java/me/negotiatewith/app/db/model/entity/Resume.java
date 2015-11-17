package me.negotiatewith.app.db.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;


@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "resumes")
public class Resume extends BaseEntity {

    private static final long serialVersionUID = -7908032307899412275L;

    @Column(name = "current_ctc", nullable = false)
    private Long currentCtc;

    @Column(name = "skills")
    private String skills;

    @Column(name = "experiences")
    private String experiences;

    @Column(name = "education")
    private String education;
}
