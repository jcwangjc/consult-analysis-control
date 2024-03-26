package com.consult.analysis.calculate.cache;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * @author : laoa
 * @describe : word管理
 * @email : laoa@markcoin.net
 */
@Data
@Builder
public class VocabularyWordModel {
    private Long code;
    private String coin;
    private Set words;
}
