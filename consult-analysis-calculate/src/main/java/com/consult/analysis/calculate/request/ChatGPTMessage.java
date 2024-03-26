package com.consult.analysis.calculate.request;

import lombok.Builder;
import lombok.Data;

/**
 * @author : laoa
 * @describe : gpt请求体
 * @email : laoa@markcoin.net
 */
@Data
@Builder
public class ChatGPTMessage {
    private String role;
    private String content;
}
