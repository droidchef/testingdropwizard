package me.negotiatewith.app.db.model.listeners;

import me.negotiatewith.app.db.model.entity.BaseEntity;
import org.joda.time.DateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;


public class BaseEntityListener {

    @PreUpdate
    public void setUpdatedAt(BaseEntity entity) {
        entity.setUpdatedAt(new DateTime());
    }

    @PrePersist
    public void setCreatedAt(BaseEntity entity) {
        DateTime dateTime = new DateTime();
        entity.setCreatedAt(dateTime);
        entity.setUpdatedAt(dateTime);
    }
}
