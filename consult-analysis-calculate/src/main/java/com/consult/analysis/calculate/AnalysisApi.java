package com.consult.analysis.calculate;

import org.jetbrains.annotations.NotNull;

/**
 * @author : laoa
 * @describe : api接口
 * @email : laoa@markcoin.net
 */
public interface AnalysisApi {
    @NotNull
    AnalysisChatGptApi chatGpt();
    @NotNull
    AnalysisFilterApi filter();

    @NotNull
    static Builder builder() {
        AnalysisApiBuilder analysisApiBuilder = new AnalysisApiBuilder();
        return analysisApiBuilder;
    }

    interface Builder{
        @NotNull
        Builder withURL(@NotNull String point);
        @NotNull
        Builder withApiKey(@NotNull String apiKey);
        @NotNull
        AnalysisApi build();
    }
}
