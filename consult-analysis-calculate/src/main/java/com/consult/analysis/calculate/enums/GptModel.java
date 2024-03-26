package com.consult.analysis.calculate.enums;

/**
 * @author : laoa
 * @describe : gpt模型
 * @email : laoa@markcoin.net
 */
public enum GptModel {
    gpt_3_5("gpt-3.5-turbo-0125"),
    gpt_4("gpt-4");
    private String version;

    GptModel(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
