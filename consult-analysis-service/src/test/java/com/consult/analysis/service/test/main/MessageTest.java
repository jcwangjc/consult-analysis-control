package com.consult.analysis.service.test.main;

import com.alibaba.fastjson.JSONObject;
import com.consult.analysis.service.enums.AnalyzeType;
import com.consult.analysis.service.event.EventMessage;

public class MessageTest {
    public static void main(String[] args) {
        EventMessage eventMessage = EventMessage.builder()
                .analyzeType(AnalyzeType.INFORMATION)
                .author("Elon Reeve Musk")
                .content("solana will launch layer2 solution next month!")
                .build();
        System.out.println(JSONObject.toJSONString(eventMessage));

        EventMessage eventMessage2 = EventMessage.builder()
                .content("binance交易所")
                .analyzeType(AnalyzeType.SUBSTANTIVE)
                .build();
        System.out.println(JSONObject.toJSONString(eventMessage2));
    }
}
