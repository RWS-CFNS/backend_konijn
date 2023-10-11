package nl.cfns.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nl.cfns.base.HeatmapGenerator;
import nl.cfns.repositories.MeasurementsRepository;
import nl.cfns.entities.Measurement;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SignalstrengthService {
	@Autowired
	private MeasurementsRepository measurementRepository;

	
    public Map<String, Double> getSignalStrengthHeatmap(int resolution) throws IOException {
        Iterable<Measurement> measurements = measurementRepository.findAll(); // Adjust query as needed
		List<Measurement> measurementList = StreamSupport.stream(measurements.spliterator(), false)
				.collect(Collectors.toList());
        return HeatmapGenerator.generateHexHeatmap(measurementList, resolution);
    }
}
