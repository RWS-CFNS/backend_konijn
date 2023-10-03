package com.example.rabbit;

import org.springframework.stereotype.Service;

import com.example.entities.Measuringbox;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MeasuringboxService {
	//private repository object for interacting with measuringbox section of database
	@Autowired	
	private MeasuringboxRepository measuringboxRepository;

	public Iterable<Measuringbox> getAllMeasuringboxes() {
	    return measuringboxRepository.findAll();
	}

	public Measuringbox getMeasuringboxById(Long id) {
	    return measuringboxRepository.findById(id).orElse(null);
	}

	public Measuringbox saveMeasuringbox(Measuringbox measuringbox) {
	    return measuringboxRepository.save(measuringbox);
	}

	public void deleteMeasuringbox(Long id) {
	    measuringboxRepository.deleteById(id);
	}

}
