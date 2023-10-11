package nl.cfns.base;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import nl.cfns.basicpojo.MeasurementPoint;
import nl.cfns.entities.Measurement;
import nl.cfns.repositories.MeasurementsRepository;

@Service
public class HeatmapGenerator {
	// create repo object for this class to access database
	@Autowired
	private MeasurementsRepository measurementRepository;

	@Async
	public CompletableFuture<List<MeasurementPoint>> generateHeatmap() {
		// Query the database and process the data to generate heatmap data from
		// measuringbox
		Iterable<Measurement> measurements = measurementRepository.findAll();

		// Convert Iterable to List
		List<Measurement> measurementList = StreamSupport.stream(measurements.spliterator(), false)
				.collect(Collectors.toList());

		// Convert Measurement objects to HeatmapDataPoint objects
		List<MeasurementPoint> heatmapData = measurementList.stream()
				.map(measurement -> new MeasurementPoint(measurement.getLatitudeInteger(),
						measurement.getLongitudeInteger(), measurement.getRSSI()))
				.collect(Collectors.toList());

		return CompletableFuture.completedFuture(heatmapData);
	}
}
