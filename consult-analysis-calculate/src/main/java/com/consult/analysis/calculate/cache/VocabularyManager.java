package com.consult.analysis.calculate.cache;

import com.hankcs.hanlp.dictionary.CustomDictionary;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : laoa
 * @describe : 字段管理
 * @email : laoa@markcoin.net
 */
@Slf4j
public abstract class VocabularyManager {

    protected Map<Long,VocabularyModel> customWords=new ConcurrentHashMap<>();

    public void loadCustomDictionary(){
        Map<Long, VocabularyModel> keywords = this.getKeywords();
        keywords.keySet().forEach(key->{
            //判断缓存中是否存在这个key
            VocabularyModel vocabularyModelOld = customWords.get(key);
            VocabularyModel vocabularyModelNew = keywords.get(key);
            Set<String> wordsNew = vocabularyModelNew.getWords();
            //不存在则添加，存在则新增
            if(vocabularyModelOld==null){
                //添加新的词汇
                loadWord(wordsNew);
                //更新缓存
                customWords.put(key,vocabularyModelNew);
            }else{
                //添加新的词汇
                Set<String> wordsOld = vocabularyModelOld.getWords();
                wordsNew.removeAll(wordsOld);
                loadWord(wordsNew);
                //跟新缓存数据
                vocabularyModelOld.getWords().addAll(wordsNew);
            }
        });
    }

    private void loadWord(Set<String> words){
        words.forEach(word->{
            boolean contains = CustomDictionary.contains(word);
            if(!contains){
                CustomDictionary.add(word);
            }
        });
    }

    protected abstract Map<Long,VocabularyModel> getKeywords();

    public Map<Long,VocabularyModel> getCustomWords() {
        return customWords;
    }
}
