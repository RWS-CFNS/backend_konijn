package nl.cfns.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import nl.cfns.entity.Celltower;

@Repository("CelltowerRepository") 
public interface CelltowerRepository extends CrudRepository<Celltower, UUID> {

}
