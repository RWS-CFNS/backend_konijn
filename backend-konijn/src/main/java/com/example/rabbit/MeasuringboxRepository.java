package com.example.rabbit;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.Measuringbox;

//repository for measuringbox for interacting with the database
@Repository
public interface MeasuringboxRepository extends CrudRepository<Measuringbox,Long> {
    List<Measuringbox> findBoxByid(Long id);
}