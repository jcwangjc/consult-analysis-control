package com.consult.analysis.service.analysis.handler;

import com.consult.analysis.calculate.enums.GptModel;
import com.consult.analysis.calculate.enums.GptRole;
import com.consult.analysis.calculate.request.ChatGPTMessage;
import com.consult.analysis.calculate.request.ChatGPTParams;
import com.consult.analysis.calculate.utils.Beans;
import com.consult.analysis.service.analysis.AnalysisHandler;
import com.consult.analysis.service.event.EventMessage;
import com.consult.analysis.service.rateLimit.RateLimitService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : laoa
 * @describe : 翻译
 * @email : laoa@markcoin.net
 */
@Slf4j
public class TranslateHandler extends AnalysisHandler {
    @Override
    protected Boolean execute(EventMessage message) {
        RateLimitService rateLimitService = Beans.getBean(RateLimitService.class);
        while (true) {
            boolean lock = rateLimitService.tryAcquire();
            if (lock) {
                List<ChatGPTMessage> messages=new ArrayList<>();
                ChatGPTMessage systemMessage = ChatGPTMessage.builder()
                        .role(GptRole.SYSTEM.getRole())
                        .content(message.getSystemTranslateMessage())
                        .build();
                ChatGPTMessage userMessage = ChatGPTMessage.builder()
                        .role(GptRole.USER.getRole())
                        .content(message.getAnalysis())
                        .build();
                messages.add(systemMessage);
                messages.add(userMessage);
                ChatGPTParams params = ChatGPTParams.builder()
                        .model(GptModel.gpt_3_5.getVersion())
                        .messages(messages)
                        .build();
                try{
                    String calculate = this.api.chatGpt().calculate(params);
                    calculate=calculate.replace("\n","")
                            .replace(" ","");
                    message.setSystemTranslateMessage(null);
                    message.setAnalysisMulti(calculate);
                }catch (Exception e){
                    log.error("...translate error ",e);
                }
                return true;
            }
        }
    }
}
