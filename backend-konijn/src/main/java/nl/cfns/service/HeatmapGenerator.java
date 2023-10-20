package nl.cfns.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import nl.cfns.basicpojo.MeasurementPoint;
import nl.cfns.entity.Measurement;
import nl.cfns.repository.MeasurementRepository;

import com.uber.h3core.H3Core;

@Service
public class HeatmapGenerator {
	//create repo object for this class to access database
	@Autowired
	private MeasurementRepository measurementRepository;

	@Async
	public CompletableFuture<List<MeasurementPoint>> generateHeatmap() {
		//Query the database and process the data to generate heatmap data from measuringbox
		Iterable<Measurement> measurements = measurementRepository.findAll();

		//Convert Iterable to List
		List<Measurement> measurementList = StreamSupport.stream(measurements.spliterator(), false)
				.collect(Collectors.toList());

		//Convert Measurement objects to HeatmapDataPoint objects
		List<MeasurementPoint> heatmapData = measurementList.stream()
				.map(measurement -> new MeasurementPoint(measurement.getLatitude(),
						measurement.getLongitude(), measurement.getRSSI()))
				.collect(Collectors.toList());

		return CompletableFuture.completedFuture(heatmapData);
	}
	
	//this function creates a heatmap using a hex shaped grid instead of rectangle-shaped grid
	//it uses the H3 library from uber. The result is a map containing (1) grid number values, and then (2) signalstrength
	//values corresponding to that specific hex shape
	@Async
    public static Map<String, Double> generateHexHeatmap(List<Measurement> signalData, int resolution) throws IOException {
        // Initialize H3Core
        H3Core h3Core = H3Core.newInstance();

        // Create a map to store aggregated signal strength data by H3 hexagon
        Map<String, Double> heatmapData = new HashMap<>();

        for (Measurement data : signalData) {
            double latitude = data.getLatitude();
            double longitude = data.getLongitude();

            // Convert latitude and longitude to H3 hexagon
            long h3Index = h3Core.geoToH3(latitude, longitude, resolution);
            String h3Hexagon = Long.toHexString(h3Index);

            //Aggregate signal strength data by H3 hexagon
            if (heatmapData.containsKey(h3Hexagon)) {
                // If the hexagon already exists in the map, update the aggregated value (e.g., take the average)
                double aggregatedValue = heatmapData.get(h3Hexagon);
                aggregatedValue = (aggregatedValue + data.getRSSI()) / 2.0; //Adjust aggregation method as needed
                heatmapData.put(h3Hexagon, aggregatedValue);
            } else {
                //If the hexagon is not in the map, add it with the signal strength value. convert from int to double
                heatmapData.put(h3Hexagon, data.getRSSI().doubleValue());
            }
        }

        return heatmapData;
    }
}
