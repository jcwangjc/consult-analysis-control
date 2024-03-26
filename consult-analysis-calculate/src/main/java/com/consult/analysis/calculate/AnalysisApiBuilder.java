package com.consult.analysis.calculate;

import com.consult.analysis.calculate.provider.AnalysisApiProvider;
import org.jetbrains.annotations.NotNull;

/**
 * @author : laoa
 * @describe : api建造者
 * @email : laoa@markcoin.net
 */
final class AnalysisApiBuilder implements AnalysisApi.Builder{
    private String point;
    private String apiKey;

    @Override
    public AnalysisApi.@NotNull Builder withURL(@NotNull String point) {
        this.point=point;
        return this;
    }

    @Override
    public AnalysisApi.@NotNull Builder withApiKey(@NotNull String apiKey) {
        this.apiKey=apiKey;
        return this;
    }

    @Override
    public @NotNull AnalysisApi build() {
        return new AnalysisApiProvider(this.point,this.apiKey);
    }
}
