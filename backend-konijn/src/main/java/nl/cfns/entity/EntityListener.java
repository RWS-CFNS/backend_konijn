package nl.cfns.entity;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

//this class helps to prevent collisions when multiple theads are trying to edit a database entry at the same time

//this annotation is for lombok
@Slf4j
public class EntityListener {

	//execute before saving into database
    @PrePersist
    public void prePersist(Object entity) {
        if (entity instanceof VersionedEntity) {
            VersionedEntity versionedEntity = (VersionedEntity) entity;
            log.debug("Handling prePersist for entity: {}", versionedEntity);
            versionedEntity.setVersion(versionedEntity.getVersion() + 1);
        }
    }

  //execute before saving into database, same function. different names for clearance
    @PreUpdate
    public void preUpdate(Object entity) {
        if (entity instanceof VersionedEntity) {
            VersionedEntity versionedEntity = (VersionedEntity) entity;
            log.debug("Handling preUpdate for entity: {}", versionedEntity);
            versionedEntity.setVersion(versionedEntity.getVersion() + 1);
        }
    }
}

