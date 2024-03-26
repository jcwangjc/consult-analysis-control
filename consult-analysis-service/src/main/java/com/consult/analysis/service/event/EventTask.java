package com.consult.analysis.service.event;

import com.alibaba.fastjson.JSON;
import com.consult.analysis.service.analysis.config.AnalysisReplace;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @author : laoa
 * @describe : 数据包装成一个个任务，放入线程池执行
 * @email : laoa@markcoin.net
 */
public class EventTask implements Runnable {
    private EventExecute eventExecute;

    private KafkaTemplate<String, Object> kafkaTemplate;

    private AnalysisReplace analysisReplace;

    private EventMessage eventMessage;

    public EventTask(EventExecute eventExecute, KafkaTemplate<String, Object> kafkaTemplate, AnalysisReplace analysisReplace, EventMessage eventMessage) {
        this.eventExecute = eventExecute;
        this.kafkaTemplate = kafkaTemplate;
        this.analysisReplace = analysisReplace;
        this.eventMessage = eventMessage;
    }

    @Override
    public void run() {
        try{
            //先处理数据
            eventExecute.execute(eventMessage);
            //判断是否需要发生给下游
            String analysisEnglish = eventMessage.getAnalysis();
            if(analysisEnglish!=null&&analysisEnglish.length()>0&&!analysisEnglish.equals(analysisReplace.getSystemMessageNoResult())){
                kafkaTemplate.send(analysisReplace.getResultTopic(), JSON.toJSONString(eventMessage));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
