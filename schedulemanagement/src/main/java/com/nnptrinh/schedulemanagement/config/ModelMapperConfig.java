package com.nnptrinh.schedulemanagement.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        // MatchingStrategies.STANDARD
        // MatchingStrategies.LOOSE
        // MatchingStrategies.STRICT
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setCollectionsMergeEnabled(false);
        return modelMapper;
    }
}
