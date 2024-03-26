package com.consult.analysis.service.analysis.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author : laoa
 * @describe : 加载一些配置
 * @email : laoa@markcoin.net
 */
@Data
@Component
public class AnalysisReplace {
    @Value("${api.chat-gpt.role-system.system-message-no-result}")
    private String systemMessageNoResult;

    @Value("${api.chat-gpt.role-system.system-message-related}")
    private String systemMessageRelated;

    @Value("${api.chat-gpt.role-system.system-message-result}")
    private String systemMessageResult;

    @Value("${api.chat-gpt.role-system.system-message-mood}")
    private String systemMessageMood;

    @Value("${spring.kafka.producer.topic}")
    private String resultTopic;

    @Value("${spring.kafka.consumer.topic}")
    private String consumerTopic;
}
