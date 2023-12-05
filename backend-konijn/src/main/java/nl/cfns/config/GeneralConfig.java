package nl.cfns.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nl.cfns.entity.EntityListener;

@Configuration
public class GeneralConfig {
	// create object to convert between entities (models) and nl.cfns.dto's
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	// insert entity listener into spring
    @Bean
    public EntityListener globalEntityListener() {
        return new EntityListener();
    }
}
