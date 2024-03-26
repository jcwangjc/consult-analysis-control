package com.consult.analysis.calculate.cache;

import lombok.Data;

import java.util.Set;

/**
 * @author : laoa
 * @describe : 关键词模型
 * @email : laoa@markcoin.net
 */
@Data
public class VocabularyModel {
    private Long code;
    private Set<String> coins;
    private Set<String> words;
}
