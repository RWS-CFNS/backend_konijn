package nl.cfns.h2service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import nl.cfns.entity.Measurement;
import nl.cfns.entity.Measuringbox;
import nl.cfns.repository.MeasurementRepository;

@Service
public class measurementService {

    private MeasurementRepository measurementRepository;

    // Get a list of Measuringbox objects
    public List<Measurement> getAllMeasuringboxes() {
		//Query the database and process the data to generate heatmap data from measuringbox
		Iterable<Measurement> measurements = measurementRepository.findAll();
		//Convert Iterable to List
		List<Measurement> measurementList = StreamSupport.stream(measurements.spliterator(), false)
				.collect(Collectors.toList());
		
		return measurementList;
    }

    // Save a Measuringbox object
    public Measurement saveMeasuringbox(Measurement measurement, Measuringbox measuringbox) {
    	//measurement.setMeasuringbox(measuringbox); //set foreign key
        return measurementRepository.save(measurement);
    }

    // Delete a Measuringbox object by its ID
    public void deleteMeasuringboxById(UUID id) {
    	measurementRepository.deleteById(id);
    }

    // Verify if a Measuringbox object exists by its ID
    public boolean doesMeasuringboxExist(UUID id) {
        return measurementRepository.existsById(id);
    }

    // Get a Measuringbox object by its ID
    public Measurement getMeasuringboxById(UUID id) {
    	return measurementRepository.findById(id).orElse(null);
    }
}
