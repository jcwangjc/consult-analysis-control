package com.consult.analysis.service.test.spring;

import com.alibaba.fastjson.JSONObject;
import com.consult.analysis.service.enums.AnalyzeType;
import com.consult.analysis.service.enums.HandlerStep;
import com.consult.analysis.service.event.EventExecute;
import com.consult.analysis.service.event.EventMessage;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EventExecuteTest {
    @Autowired
    EventExecute eventExecute;

    @Value("${api.chat-gpt.role-system.system-message}")
    private String systemMessage;

    @Test
    public void test(){
        EventMessage eventMessage = EventMessage.builder()
                .content("solana will launch layer2 solution next month!")
//                .systemMessage(systemMessage)
                .analyzeType(AnalyzeType.INFORMATION)
                .build();
        System.out.println(JSONObject.toJSONString(eventMessage));
//        eventExecute.execute(eventMessage);

        //{"content":"solana will launch layer2 solution next month!"}
    }
}
