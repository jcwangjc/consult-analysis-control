package com.consult.analysis.service.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.consult.analysis.service.analysis.config.AnalysisReplace;
import com.consult.analysis.service.event.EventExecute;
import com.consult.analysis.service.event.EventMessage;
import com.consult.analysis.service.event.EventTask;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;

/**
 * @author : laoa
 * @describe : 本类的listen方法，又多个kafka的消费线程同时处理，具体线程数量看kafka的concurrency配置项，
 *  需要根据kafka partition数量来确定concurrency的大小：服务节点数量 X concurrency <=partition数量
 * @email : laoa@markcoin.net
 */
@Component
public class KafkaConsumerConfig {

    @Autowired
    private EventExecute eventExecute;

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private AnalysisReplace analysisReplace;

    @Autowired
    private ExecutorService executorService;

    @KafkaListener(topics = {"${spring.kafka.consumer.topic}"}, groupId = "${spring.kafka.consumer.group-id}")
    public void listen(ConsumerRecord<?, String> record, Consumer consumer) {
        String value = record.value();
        try{
            EventMessage eventMessage = JSONObject.parseObject(value, EventMessage.class);
            executorService.execute(new EventTask(eventExecute,kafkaTemplate,analysisReplace,eventMessage));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //注意和KafkaListener中的topic保持一致
    @Bean
    public NewTopic consumerTopic() {
        return TopicBuilder.name(analysisReplace.getConsumerTopic())
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic resultTopic() {
        return TopicBuilder.name(analysisReplace.getResultTopic())
                .partitions(3)
                .replicas(1)
                .build();
    }
}
