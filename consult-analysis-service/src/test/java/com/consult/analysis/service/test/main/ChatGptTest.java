package com.consult.analysis.service.test.main;

import com.consult.analysis.calculate.AnalysisApi;
import com.consult.analysis.calculate.enums.GptModel;
import com.consult.analysis.calculate.enums.GptRole;
import com.consult.analysis.calculate.request.ChatGPTMessage;
import com.consult.analysis.calculate.request.ChatGPTParams;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChatGptTest {
    public static void main(String[] args) throws IOException {
        //构建API
        AnalysisApi analysisApi = AnalysisApi.builder()
                .withURL("https://api.openai.com/v1/chat/completions")
                .withApiKey("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")
                .build();

        //构建查询参数
        List<ChatGPTMessage> messages=new ArrayList<>();
        ChatGPTMessage systemMessage = ChatGPTMessage.builder()
                .role(GptRole.SYSTEM.getRole())
                .content("您是一个区块链行业领导者，您将收到一条推文，您的任务是判断是否和金融市场或者区块链市场相关，相关则用中文分析推文对市场的影响，同时判断推文情绪为'积极'、'消极'还是'中性'，否则回答'no-result'。最终用{'是否相关':相关,'分析结果':分析结果,'情绪类型':'消极'}的JSON格式返回给我")
                .build();
        ChatGPTMessage userMessage = ChatGPTMessage.builder()
                .role(GptRole.USER.getRole())
                .content("BTC价格今日已经涨到了50000U以上!")
                .build();
        messages.add(systemMessage);
        messages.add(userMessage);
        ChatGPTParams params = ChatGPTParams.builder()
                .model(GptModel.gpt_3_5.getVersion())
                .messages(messages)
                .build();
        analysisApi.chatGpt().calculate(params);
    }
}
