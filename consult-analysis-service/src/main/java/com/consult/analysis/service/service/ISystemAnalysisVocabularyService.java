package com.consult.analysis.service.service;

import com.consult.analysis.calculate.cache.VocabularyWordModel;
import com.consult.analysis.service.domain.SystemAnalysisVocabulary;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author laoA
 * @since 2024-02-23
 */
public interface ISystemAnalysisVocabularyService extends IService<SystemAnalysisVocabulary> {
    public List<VocabularyWordModel> vocabularyCollect();
}
