package com.consult.analysis.service.rateLimit;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author : laoa
 * @describe : 限流服务
 * @email : laoa@markcoin.net
 */
@Configuration
@EnableConfigurationProperties(value = {RateLimitConfig.class})
@Data
public class RateLimitService {

    private RedissonClient redissonClient;
    private RateLimitConfig rateLimitConfig;

    public RateLimitService(RateLimitConfig rateLimitConfig) {
        Config config = new Config();
        config.useSingleServer()
                .setAddress(rateLimitConfig.getLimit().getAddress())
                .setPassword(rateLimitConfig.getLimit().getPassword());
        this.redissonClient = Redisson.create(config);
        this.rateLimitConfig = rateLimitConfig;
    }

    public boolean tryAcquire() {
        RRateLimiter rateLimiter = redissonClient.getRateLimiter(rateLimitConfig.getLimit().getKey());
        rateLimiter.trySetRate(RateType.OVERALL, rateLimitConfig.getLimit().getPermits(), rateLimitConfig.getLimit().getInterval(), RateIntervalUnit.MINUTES);
        return rateLimiter.tryAcquire();
    }
}
