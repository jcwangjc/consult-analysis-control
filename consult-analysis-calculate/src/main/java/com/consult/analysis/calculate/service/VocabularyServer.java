package com.consult.analysis.calculate.service;

import com.consult.analysis.calculate.cache.VocabularyWordModel;

import java.util.List;

/**
 * @author : laoa
 * @describe : 字典管理接口
 * @email : laoa@markcoin.net
 */
public interface VocabularyServer {
    List<VocabularyWordModel> vocabularyCollect();
}
