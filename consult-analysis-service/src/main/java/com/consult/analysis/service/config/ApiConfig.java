package com.consult.analysis.service.config;

import com.consult.analysis.calculate.AnalysisApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : laoa
 * @describe : api实例
 * @email : laoa@markcoin.net
 */
@Configuration
public class ApiConfig {

    @Value("${api.chat-gpt.key}")
    private String key;
    @Value("${api.chat-gpt.point}")
    private String point;

    @Bean
    public AnalysisApi getAnalysisApi(){
        AnalysisApi analysisApi = AnalysisApi.builder().withApiKey(key).withURL(point).build();
        return analysisApi;
    }
}
