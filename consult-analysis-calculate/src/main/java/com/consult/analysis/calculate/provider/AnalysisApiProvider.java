package com.consult.analysis.calculate.provider;

import com.consult.analysis.calculate.AnalysisApi;
import com.consult.analysis.calculate.AnalysisChatGptApi;
import com.consult.analysis.calculate.AnalysisFilterApi;
import org.jetbrains.annotations.NotNull;

/**
 * @author : laoa
 * @describe : api实例模型
 * @email : laoa@markcoin.net
 */
public final class AnalysisApiProvider implements AnalysisApi {

    @NotNull
    private AnalysisChatGptApi analysisChatGptApi;
    @NotNull
    private AnalysisFilterApi analysisFilterApi;

    public AnalysisApiProvider(String point,String apiKey) {
        analysisChatGptApi=new AnalysisChatGptApiProvider(point,apiKey);
        analysisFilterApi=new AnalysisFilterApiProvider();
    }

    @Override
    public @NotNull AnalysisChatGptApi chatGpt() {
        return analysisChatGptApi;
    }

    @Override
    public @NotNull AnalysisFilterApi filter() {
        return analysisFilterApi;
    }
}
