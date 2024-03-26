package com.consult.analysis.service.service.impl;

import com.consult.analysis.calculate.cache.VocabularyModel;
import com.consult.analysis.calculate.cache.VocabularyWordModel;
import com.consult.analysis.service.domain.SystemAnalysisVocabulary;
import com.consult.analysis.service.mapper.SystemAnalysisVocabularyMapper;
import com.consult.analysis.service.service.ISystemAnalysisVocabularyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.consult.analysis.service.vo.SystemAnalysisVocabularyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author laoA
 * @since 2024-02-23
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SystemAnalysisVocabularyServiceImpl extends ServiceImpl<SystemAnalysisVocabularyMapper, SystemAnalysisVocabulary> implements ISystemAnalysisVocabularyService {

    @Autowired
    private SystemAnalysisVocabularyMapper systemAnalysisVocabularyMapper;

    @Override
    public List<VocabularyWordModel> vocabularyCollect() {
        List<SystemAnalysisVocabularyVO> list = systemAnalysisVocabularyMapper.getList();
        List<VocabularyWordModel> collect = list.stream().map(systemAnalysisVocabularyVO -> {
            Set<String> wordsSet = new HashSet<>();
            Long sid = systemAnalysisVocabularyVO.getSid();
            String root_name = systemAnalysisVocabularyVO.getRootName();
            String major_name = systemAnalysisVocabularyVO.getMajorName();
            String words = systemAnalysisVocabularyVO.getWords();
            String coin = systemAnalysisVocabularyVO.getCoin();
            if(coin!=null&&coin.length()>0){
                coin=coin.toLowerCase();
                wordsSet.add(coin);
            }
            if (words != null && words.length() > 0) {
                wordsSet = new HashSet<>(Arrays.asList(words.split(";")));
                wordsSet=wordsSet.stream().map(s -> {
                    return s.toUpperCase();
                }).collect(Collectors.toSet());
            }
            if(root_name!=null&&root_name.length()>0){
                wordsSet.add(root_name.toLowerCase());
            }
            if(major_name!=null&&major_name.length()>0){
                wordsSet.add(major_name.toLowerCase());
            }
            VocabularyWordModel build = VocabularyWordModel.builder()
                    .code(sid)
                    .coin(coin)
                    .words(wordsSet)
                    .build();
            return build;
        }).collect(Collectors.toList());
        return collect;
    }
}
