package nl.cfns.entity;

import java.util.UUID;

//this is a little helper class to define which entities define a version. 
//this enables the use of the entitylistener class
public interface VersionedEntity {
    Integer getVersion();
    void setVersion(Integer version);
}
