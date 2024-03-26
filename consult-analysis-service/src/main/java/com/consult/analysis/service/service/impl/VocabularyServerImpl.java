package com.consult.analysis.service.service.impl;

import com.consult.analysis.calculate.cache.VocabularyWordModel;
import com.consult.analysis.calculate.service.VocabularyServer;
import com.consult.analysis.service.service.ISystemAnalysisVocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author : laoa
 * @describe : 字典服务
 * @email : laoa@markcoin.net
 */
@Service
public class VocabularyServerImpl implements VocabularyServer {
    @Autowired
    private ISystemAnalysisVocabularyService iSystemAnalysisVocabularyService;

    @Override
    public List<VocabularyWordModel> vocabularyCollect() {
        List<VocabularyWordModel> vocabularyModels = iSystemAnalysisVocabularyService.vocabularyCollect();
        return vocabularyModels;
    }
}
