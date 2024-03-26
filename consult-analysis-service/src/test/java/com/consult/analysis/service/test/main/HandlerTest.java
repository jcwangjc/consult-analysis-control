package com.consult.analysis.service.test.main;

import com.consult.analysis.calculate.AnalysisApi;
import com.consult.analysis.service.analysis.AnalysisHandler;
import com.consult.analysis.service.analysis.handler.ChatGptHandler;
import com.consult.analysis.service.analysis.handler.FilterHandler;
import com.consult.analysis.service.event.EventMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HandlerTest {
    public static void main(String[] args) {

        AnalysisApi analysisApi = AnalysisApi.builder()
                .withURL("https://api.openai.com/v1/chat/completions")
                .withApiKey("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")
                .build();

        EventMessage message = EventMessage
                .builder()
                .title("solana layer2解决方案")
                .author("Elon Reeve Musk")
                .content("solana will launch layer2 solution next month!！")
                .systemMessage("您将收到一条推文，您的任务是判断是否和金融市场或者区块链市场有关，回答yes或者or。如果yes的话，则请根据历史数据给予简单分析，并给出投资建议。")
                .build();
        AnalysisHandler.Builder builder = new AnalysisHandler.Builder(analysisApi);
        builder.addHandler(new FilterHandler())
                .addHandler(new ChatGptHandler())
                .build()
                .doHandler(message);
        log.info("....{}",message);

        // TODO 多线程异步处理，返回结果
    }
}
