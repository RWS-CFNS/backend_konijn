package nl.cfns.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.cfns.basicpojo.MeasurementPoint;
import nl.cfns.service.HeatmapGeneratorService;
import nl.cfns.service.SignalstrengthService;

@RestController
@RequestMapping("/heatmap")
public class HeatmapController {
	@Autowired
	private HeatmapGeneratorService heatmapService;
	
	@Autowired
	private SignalstrengthService signalstrengthService;

	// access this mapping at http://localhost:8090/heatmap/data
	@GetMapping("/data")
	public CompletableFuture<List<MeasurementPoint>> getHeatmapData() {
		return heatmapService.generateHeatmap();
	}
	
	// access this mapping at http://localhost:8090/heatmap/h3data
	@GetMapping("/h3data")
	public Map<String, Double> geth3Mapdata() throws IOException{
		return signalstrengthService.getSignalStrengthHeatmap();
	}
}

