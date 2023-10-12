package nl.cfns.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import nl.cfns.base.DataSimulator;
import nl.cfns.entity.Celltower;
import nl.cfns.repository.CelltowerRepository;

@Configuration
public class SimulatorConfig {
	@Autowired
	private CelltowerRepository celltowerRepository;
	
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
