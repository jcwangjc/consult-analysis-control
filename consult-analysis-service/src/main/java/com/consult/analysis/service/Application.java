package com.consult.analysis.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : laoa
 * @describe : 资讯分析引擎
 *  需要注意的是，作为kafka的消费者客户端，单一节点采用的多线程模式，通过配置文件中的concurrency属性配置的数量，如果是多个节点部署，则：
 *  concurrency * 节点数量 < kafka partition数量
 * @email : laoa@markcoin.net
 */
@SpringBootApplication
@MapperScan({"com.consult.analysis.service.mapper"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}