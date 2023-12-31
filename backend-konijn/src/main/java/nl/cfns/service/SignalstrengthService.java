package nl.cfns.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import nl.cfns.entity.Measurement;
import nl.cfns.repository.MeasurementsRepository;

import java.io.IOException;
import java.util.ArrayList;
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
  
    //function to filter based on values given by the user. if the parameter is null, it indicates that the user does not 
    //wish to filter based on that member of the entity class
    public List<Measurement> filterMeasurements(
            Integer minLatency, Integer maxLatency,
            Float minUpload, Float maxUpload,
            Float minDownload, Float maxDownload,
            Integer minRSSI, Integer maxRSSI,
            Integer minRSRQ, Integer maxRSRQ,
            Integer minRSRP, Integer maxRSRP,
            Integer minSINR, Integer maxSINR,
            String mnoString,
            Double minLatitude, Double maxLatitude,
            Double minLongitude, Double maxLongitude) {
    	//create List to keep track of valid Measurements
        List<Measurement> filteredMeasurements = new ArrayList<>();

        //filter based on each member of Measurement class
        if (minLatency != null && maxLatency != null) {
            filteredMeasurements.addAll(measurementRepository.findByLatencyBetween(minLatency, maxLatency));
        }

        if (minUpload != null && maxUpload != null) {
            filteredMeasurements.addAll(measurementRepository.findByUploadBetween(minUpload, maxUpload));
        }

        if (minDownload != null && maxDownload != null) {
            filteredMeasurements.addAll(measurementRepository.findByDownloadBetween(minDownload, maxDownload));
        }

        if (minRSSI != null && maxRSSI != null) {
            filteredMeasurements.addAll(measurementRepository.findByRSSIBetween(minRSSI, maxRSSI));
        }

        if (minRSRQ != null && maxRSRQ != null) {
            filteredMeasurements.addAll(measurementRepository.findByRSRQBetween(minRSRQ, maxRSRQ));
        }

        if (minRSRP != null && maxRSRP != null) {
            filteredMeasurements.addAll(measurementRepository.findByRSRPBetween(minRSRP, maxRSRP));
        }

        if (minSINR != null && maxSINR != null) {
            filteredMeasurements.addAll(measurementRepository.findBySINRBetween(minSINR, maxSINR));
        }

        if (mnoString != null && !mnoString.isEmpty()) {
            filteredMeasurements.addAll(measurementRepository.findByMnoString(mnoString));
        }

        if (minLatitude != null && maxLatitude != null) {
            filteredMeasurements.addAll(measurementRepository.findByLatitudeBetween(minLatitude, maxLatitude));
        }

        if (minLongitude != null && maxLongitude != null) {
            filteredMeasurements.addAll(measurementRepository.findByLongitudeBetween(minLongitude, maxLongitude));
        }

        //return list of valid measurements
        return filteredMeasurements;
    }
}
