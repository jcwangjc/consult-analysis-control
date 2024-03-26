package com.consult.analysis.calculate;

import com.consult.analysis.calculate.provider.model.FilterResult;

/**
 * @author : laoa
 * @describe : 过滤 接口
 * @email : laoa@markcoin.net
 */
public interface AnalysisFilterApi {
    FilterResult match(String content);
}
