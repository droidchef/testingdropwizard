package me.negotiatewith.app.db.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;


@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    private static final long serialVersionUID = -7908033907899412275L;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL, optional = true)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profile;

}
