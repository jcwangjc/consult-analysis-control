package com.consult.analysis.calculate.provider;

import com.alibaba.fastjson.JSONObject;
import com.consult.analysis.calculate.AnalysisChatGptApi;
import com.consult.analysis.calculate.request.ChatGPTParams;
import com.consult.analysis.calculate.response.ChatGPTResponse;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author : laoa
 * @describe : gpt模型
 * @email : laoa@markcoin.net
 */
@Slf4j
public final class AnalysisChatGptApiProvider implements AnalysisChatGptApi {

    private static String MEDIA_TYPE="application/json";

    private String point;
    private String apiKey;

    private OkHttpClient client;
    private MediaType mediaType;

    public AnalysisChatGptApiProvider(String point,String apiKey) {
        this.point=point;
        this.apiKey=apiKey;

        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(40, TimeUnit.SECONDS)
                .writeTimeout(40, TimeUnit.SECONDS)
                .build();
        mediaType = MediaType.parse(MEDIA_TYPE);
    }

    @Override
    public String calculate(ChatGPTParams chatGPTParams) throws IOException {
        String requestBody=JSONObject.toJSONString(chatGPTParams);
        RequestBody body = RequestBody.create(requestBody, mediaType);
        Request request = new Request.Builder()
                .url(point)
                .post(body)
                .addHeader("Authorization", "Bearer " + apiKey)
                .addHeader("Content-Type", MEDIA_TYPE)
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected response code: " + response);
        }
        String jsonResponse = response.body().string();
        Gson gson = new Gson();
        ChatGPTResponse gptResponse = gson.fromJson(jsonResponse, ChatGPTResponse.class);
        log.info("...gpt result->{}",gptResponse.getChoices()[0].getMessage().getContent());
        return gptResponse.getChoices()[0].getMessage().getContent();
    }
}
