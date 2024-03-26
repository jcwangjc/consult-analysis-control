package com.consult.analysis.service.event;

import com.alibaba.fastjson.JSONObject;
import com.consult.analysis.calculate.AnalysisApi;
import com.consult.analysis.service.analysis.AnalysisHandler;
import com.consult.analysis.service.analysis.handler.ChatGptHandler;
import com.consult.analysis.service.analysis.handler.FilterHandler;
import com.consult.analysis.service.enums.AnalyzeType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author : laoa
 * @describe : 事件执行器
 * @email : laoa@markcoin.net
 */
@Slf4j
@Component
public class EventExecute {

    @Value("${api.chat-gpt.role-system.system-message}")
    private String systemMessage;

    @Value("${api.chat-gpt.role-system.system-message-explain}")
    private String systemMessageExplain;

    @Autowired
    private AnalysisApi analysisApi;

    public void execute(EventMessage message){
        log.info("...begin execute message ->{}", JSONObject.toJSONString(message));
        if(message.getAnalyzeType().equals(AnalyzeType.INFORMATION)){
//            if(message.getAuthor()!=null&&message.getAuthor().length()>0){
//                message.setContent(message.getAuthor()+":"+message.getContent());
//            }
            message.setSystemMessage(systemMessage);
//            message.setSystemTranslateMessage(iSystemAnalysisLanguageService.getTemplate());
        }else{
            message.setSystemMessage(systemMessageExplain);
        }

        new AnalysisHandler.Builder(analysisApi)
                .addHandler(new FilterHandler())
                .addHandler(new ChatGptHandler())
//                .addHandler(new TranslateHandler())
                .build()
                .doHandler(message);

        log.info("...end execute message ->{}", JSONObject.toJSONString(message));
    }
}
