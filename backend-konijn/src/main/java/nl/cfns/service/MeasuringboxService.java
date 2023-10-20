package nl.cfns.service;

import org.springframework.stereotype.Service;

import nl.cfns.entity.Testbox;
import nl.cfns.repository.MeasuringboxRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MeasuringboxService {
	//private repository object for interacting with measuringbox section of database
	@Autowired	
	private MeasuringboxRepository measuringboxRepository;

	public Iterable<Testbox> getAllMeasuringboxes() {
	    return measuringboxRepository.findAll();
	}

	public Testbox getMeasuringboxById(Long id) {
	    return measuringboxRepository.findById(id).orElse(null);
	}

	public Testbox saveMeasuringbox(Testbox measuringbox) {
	    return measuringboxRepository.save(measuringbox);
	}

	public void deleteMeasuringbox(Long id) {
	    measuringboxRepository.deleteById(id);
	}

}
