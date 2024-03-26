package com.consult.analysis.service.rateLimit;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author : laoa
 * @describe : 限流配置
 * @email : laoa@markcoin.net
 */
@ConfigurationProperties(prefix = "api.chat-gpt",ignoreInvalidFields=true)
@Data
public class RateLimitConfig {

    private LimitConfig limit;

    @Data
    public static class LimitConfig{
        @Value("${permits}")
        private long permits;
        @Value("${interval}")
        private long interval;
        @Value("${key}")
        private String key;
        @Value("${address}")
        private String address;
        @Value("${password}")
        private String password;
    }
}
