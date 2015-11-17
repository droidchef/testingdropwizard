package me.negotiatewith.app.db.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import me.negotiatewith.app.core.utils.JodaDateTimeConverter;
import me.negotiatewith.app.db.model.listeners.BaseEntityListener;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;


@Data
@MappedSuperclass
@EntityListeners(BaseEntityListener.class)
@EqualsAndHashCode(of = {"id"})
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at")
    @Convert(converter = JodaDateTimeConverter.class)
    private DateTime createdAt;

    @Column(name = "updated_at")
    @Convert(converter = JodaDateTimeConverter.class)
    private DateTime updatedAt;
}
