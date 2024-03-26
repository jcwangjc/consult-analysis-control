package com.consult.analysis.calculate.provider.model;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * @author : laoa
 * @describe : 关键词匹配结果
 * @email : laoa@markcoin.net
 */
@Data
@Builder
public class FilterResult {
    private Boolean result;
    private Set<Long> codes;
    private Set<String> coins;
}
