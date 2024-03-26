package com.consult.analysis.service.analysis.handler;

import com.alibaba.fastjson.JSONObject;
import com.consult.analysis.calculate.AnalysisApi;
import com.consult.analysis.calculate.enums.GptModel;
import com.consult.analysis.calculate.enums.GptRole;
import com.consult.analysis.calculate.request.ChatGPTMessage;
import com.consult.analysis.calculate.request.ChatGPTParams;
import com.consult.analysis.calculate.utils.Beans;
import com.consult.analysis.service.analysis.AnalysisHandler;
import com.consult.analysis.service.analysis.config.AnalysisReplace;
import com.consult.analysis.service.analysis.content.AnalysisContent;
import com.consult.analysis.service.enums.AnalyzeType;
import com.consult.analysis.service.enums.RelatedResult;
import com.consult.analysis.service.event.EventMessage;
import com.consult.analysis.service.rateLimit.RateLimitService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author : laoa
 * @describe : 资讯分析
 * @email : laoa@markcoin.net
 */
@Slf4j
public class ChatGptHandler extends AnalysisHandler {

    @Override
    protected Boolean execute(EventMessage message){
        RateLimitService rateLimitService = Beans.getBean(RateLimitService.class);
        while (true){
            boolean lock = rateLimitService.tryAcquire();
            if(lock){
                List<ChatGPTMessage> messages=new ArrayList<>();
                ChatGPTMessage systemMessage = ChatGPTMessage.builder()
                        .role(GptRole.SYSTEM.getRole())
                        .content(message.getSystemMessage())
                        .build();
                ChatGPTMessage userMessage = ChatGPTMessage.builder()
                        .role(GptRole.USER.getRole())
                        .content(message.getContent())
                        .build();
                messages.add(systemMessage);
                messages.add(userMessage);
                ChatGPTParams params = ChatGPTParams.builder()
                        .model(GptModel.gpt_4.getVersion())
                        .messages(messages)
                        .build();
                AnalysisReplace analysisReplace = Beans.getBean(AnalysisReplace.class);
                try{
                    String calculate = this.api.chatGpt().calculate(params);
                    message.setSystemMessage(null);
                    if(message.getAnalyzeType().equals(AnalyzeType.INFORMATION)){
                        if(calculate!=null&&calculate.trim().equals(analysisReplace.getSystemMessageNoResult())){
                            message.setRelated(RelatedResult.NO.isResult());
                        }else{
                            message.setRelated(RelatedResult.YES.isResult());
                        }
                        calculate=calculate.replace(analysisReplace.getSystemMessageRelated(), AnalysisContent.RELATIVITY)
                                .replace(analysisReplace.getSystemMessageResult(),AnalysisContent.RESULT)
                                .replace(analysisReplace.getSystemMessageMood(),AnalysisContent.MOOD)
                                .replace("\n","")
                                .replace(" ","")
                                .toLowerCase();
                        HashMap hashMap = JSONObject.parseObject(calculate, HashMap.class);
                        message.setAnalysis((String)hashMap.get(AnalysisContent.RESULT));
                        message.setMood((String)hashMap.get(AnalysisContent.MOOD));
                    }else{
                        message.setAnalysis(calculate);
                    }
                }catch (Exception e){
                    log.error("...call chat-gpt interface error",e);
                    return false;
                }
                return true;
            }
        }
    }
}
