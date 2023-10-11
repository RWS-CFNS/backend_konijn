package nl.cfns.base;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.cfns.basicpojo.MeasurementPoint;

@RestController
@RequestMapping("/heatmap")
public class HeatmapController {
	@Autowired
	private HeatmapGenerator heatmapService;

	// access this mapping at http://localhost:8090/heatmap/data
	@GetMapping("/data")
	public CompletableFuture<List<MeasurementPoint>> getHeatmapData() {
		return heatmapService.generateHeatmap();
	}
}

