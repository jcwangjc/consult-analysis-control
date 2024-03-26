package com.consult.analysis.calculate;

import com.consult.analysis.calculate.request.ChatGPTParams;

import java.io.IOException;

/**
 * @author : laoa
 * @describe : gpt 接口
 * @email : laoa@markcoin.net
 */
public interface AnalysisChatGptApi {
    String calculate(ChatGPTParams chatGPTParams) throws IOException;
}
