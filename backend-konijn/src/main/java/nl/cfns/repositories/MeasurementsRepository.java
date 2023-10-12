package nl.cfns.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import nl.cfns.entities.Measurement;

@Repository("MeasurementsRepository")
public interface MeasurementsRepository  extends CrudRepository<Measurement,Long> {
    //Filter measurements based on minimum and maximum values
    List<Measurement> findByLatencyBetween(Integer minLatency, Integer maxLatency);
    List<Measurement> findByUploadBetween(Float minUpload, Float maxUpload);
    List<Measurement> findByDownloadBetween(Float minDownload, Float maxDownload);
    List<Measurement> findByRSSIBetween(Integer minRSSI, Integer maxRSSI);
    List<Measurement> findByRSRQBetween(Integer minRSRQ, Integer maxRSRQ);
    List<Measurement> findByRSRPBetween(Integer minRSRP, Integer maxRSRP);
    List<Measurement> findBySINRBetween(Integer minSINR, Integer maxSINR);

    //Filter measurements by specific MNO string
    List<Measurement> findByMnoString(String mnoString);

    //Filter measurements based on minimum and maximum latitude values
    List<Measurement> findByLatitudeBetween(Double minLatitude, Double maxLatitude);
    // Filter measurements based on minimum and maximum longitude values
    List<Measurement> findByLongitudeBetween(Double minLongitude, Double maxLongitude);
}
