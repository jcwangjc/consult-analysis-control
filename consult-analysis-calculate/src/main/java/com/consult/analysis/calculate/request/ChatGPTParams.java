package com.consult.analysis.calculate.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author : laoa
 * @describe : gpt请求体参数
 * @email : laoa@markcoin.net
 */
@Data
@Builder
public class ChatGPTParams {
    private String model;
    private List<ChatGPTMessage> messages;
}
