package nl.cfns.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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
	
	private Map<String, Double> generatedHeatmap;

	//generate heatmap every x milliseconds
	@Async
	@Scheduled(fixedRate = 5000)
	public void generateHeatmap() throws IOException {
        Iterable<Measurement> measurements = measurementRepository.findAll(); //take all measurements
		List<Measurement> measurementList = StreamSupport.stream(measurements.spliterator(), false)//()convert to list
				.collect(Collectors.toList());
		generatedHeatmap = HeatmapGenerator.generateHexHeatmap(measurementList, 10);	//update object generatedHeatmap	
	}
	
    public Map<String, Double> getSignalStrengthHeatmap() throws IOException {
        return generatedHeatmap;
    }
}
