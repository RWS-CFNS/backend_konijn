package nl.cfns.simulate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.java.Log;
import nl.cfns.entity.Celltower;
import nl.cfns.entity.Measurement;
import nl.cfns.entity.Measuringbox;
import nl.cfns.repository.CelltowerRepository;
import nl.cfns.repository.MeasurementRepository;
import nl.cfns.repository.MeasuringboxRepository;

@Configuration
@Log
public class SimulatorConfig {
	public static Measuringbox simulatorMeasuringbox = new Measuringbox();
	public static Measurement simulatorMeasurement = new Measurement();
	
	//private repository object for interacting with measuringbox section of database
	@Autowired	
	private CelltowerRepository celltowerRepository;

//	@Autowired	
//	private MeasurementRepository measurementRepository;
//	
//	@Autowired	
//	private MeasuringboxRepository measuringboxRepository;

	//this function creates objects that can be used as foreing key
	//note: startup of the program fails completely if values used are invalid
	@Bean
	void generateSimulatorID() {
		simulatorMeasuringbox.generateNewId();
		simulatorMeasuringbox.setMnc(2); 
		simulatorMeasuringbox.setMcc(2); 
		simulatorMeasuringbox.setLac(2); 
		simulatorMeasuringbox.setLongitude(2.0); 
		simulatorMeasuringbox.setLatitude(2.0); 
		simulatorMeasuringbox.setStatus(Measuringbox.MeasuringboxStatus.ACTIVE); 
		simulatorMeasuringbox.setSimulated(true);
		// measuringboxRepository.save(simulatorMeasuringbox);
		
		simulatorMeasurement.generateNewId();
		//simulatorMeasurement.setMeasuringbox(SimulatorConfig.simulatorMeasuringbox);
		simulatorMeasurement.setTime(new Timestamp(System.currentTimeMillis())); 
		simulatorMeasurement.setLatency(2); 
		simulatorMeasurement.setUpload((float) 2); 
	    simulatorMeasurement.setDownload((float) 2.0); 
	    simulatorMeasurement.setRSSI(-2); 
	    simulatorMeasurement.setRSRQ(-2); 
	    simulatorMeasurement.setRSRP(-2); 
	    simulatorMeasurement.setSINR(10); 
	    simulatorMeasurement.setMnoString("fakka"); 
	    simulatorMeasurement.setLongitude(2.0); 
	    simulatorMeasurement.setLatitude(2.0); 
	    simulatorMeasurement.setSimulated(true);
		//simulatorMeasurement.setMeasuringbox(simulatorMeasuringbox);
		// measurementRepository.save(simulatorMeasurement);
		
		log.info("the box for reference is " + simulatorMeasuringbox.toString());
		log.info("the measurement for reference is " + simulatorMeasurement.toString());
	}
	
	@Bean
	void createcelltowers() {
		//fill celltower array with randomly generated celltowers
		List<Celltower> celltowerList = new ArrayList<Celltower>();;
		for(Integer i=0; i<=20; i=i+1) {
			celltowerList.add(DataSimulator.generateRandomCelltower());
		}
		//replace towers in database
		celltowerRepository.deleteAll();
		celltowerRepository.saveAll(celltowerList);
		System.out.println("celltowers replaced");
	}
	
	
}
