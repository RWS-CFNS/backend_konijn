package com.example.rabbit;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MeasuringboxService {


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
