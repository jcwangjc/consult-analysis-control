package com.consult.analysis.calculate.provider;

import com.consult.analysis.calculate.AnalysisFilterApi;
import com.consult.analysis.calculate.cache.VocabularyManager;
import com.consult.analysis.calculate.cache.VocabularyModel;
import com.consult.analysis.calculate.provider.model.FilterResult;
import com.consult.analysis.calculate.utils.Beans;
import com.hankcs.hanlp.summary.TextRankKeyword;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : laoa
 * @describe : 关键字匹配模型
 * @email : laoa@markcoin.net
 */
@Slf4j
public final class AnalysisFilterApiProvider implements AnalysisFilterApi {
    @Override
    public FilterResult match(String content) {
        //定义返回结果
        FilterResult result = FilterResult.builder()
                .result(false)
                .codes(new HashSet<>())
                .coins(new HashSet<>())
                .build();
        //内容分词
        List<String> keywords = TextRankKeyword.getKeywordList(content,content.length());
        log.info("keywords is ---> {}",keywords);
        //关键词匹配
        VocabularyManager vocabularyManager = Beans.getBean(VocabularyManager.class);
        Map<Long, VocabularyModel> customWords = vocabularyManager.getCustomWords();
        customWords.forEach((k,v)->{
            Long code = v.getCode();
            Set<String> words = v.getWords();
            Set<String> coins = v.getCoins();
            //匹配
            Set<String> keywordsSet=new HashSet<>(keywords);
            keywordsSet = keywordsSet.stream().map(s -> {
                return s.toLowerCase();
            }).collect(Collectors.toSet());
            keywordsSet.retainAll(words);
            if(keywordsSet.size()>0){
                result.setResult(true);
                result.getCodes().add(code);
                //匹配币种
                Set<String> coinsTemp=new HashSet<>(coins);
                coinsTemp.retainAll(keywordsSet);
                result.getCoins().addAll(coinsTemp);
            }
        });
        return result;
    }
}
