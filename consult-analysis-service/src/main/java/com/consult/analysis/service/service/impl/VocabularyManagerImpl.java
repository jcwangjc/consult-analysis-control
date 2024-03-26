package com.consult.analysis.service.service.impl;

import com.consult.analysis.calculate.cache.VocabularyManager;
import com.consult.analysis.calculate.cache.VocabularyModel;
import com.consult.analysis.calculate.cache.VocabularyWordModel;
import com.consult.analysis.calculate.service.VocabularyServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : laoa
 * @describe : 字典管理
 * @email : laoa@markcoin.net
 */
@Slf4j
@Component
public class VocabularyManagerImpl extends VocabularyManager {
    @Autowired
    private VocabularyServer vocabularyServer;

    @Override
    protected Map<Long, VocabularyModel> getKeywords() {
        List<VocabularyWordModel> vocabularyModels = vocabularyServer.vocabularyCollect();
        Map<Long,VocabularyModel> keywords=new HashMap<>();
        for(VocabularyWordModel vocabularyWordModel:vocabularyModels){
            VocabularyModel vocabularyModel = keywords.get(vocabularyWordModel.getCode());
            if(vocabularyModel==null){
                vocabularyModel=new VocabularyModel();
                vocabularyModel.setCoins(new HashSet<>());
                vocabularyModel.setWords(new HashSet<>());
                vocabularyModel.setCode(vocabularyWordModel.getCode());
                keywords.put(vocabularyWordModel.getCode(),vocabularyModel);
            }
            String coin = vocabularyWordModel.getCoin();
            if(coin!=null&&coin.length()>0){
                vocabularyModel.getCoins().add(coin.toLowerCase());
                vocabularyModel.getWords().add(coin.toLowerCase());
            }

            Set<String> words = vocabularyWordModel.getWords();
            if(words!=null&&words.size()>0){
                if(words.contains("")){
                    words.remove("");
                }
                words=words.stream().map(s -> s.toLowerCase()).collect(Collectors.toSet());
                vocabularyModel.getWords().addAll(words);
            }
        }
        return keywords;
    }
}
