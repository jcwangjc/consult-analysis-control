package com.consult.analysis.service.mapper;

import com.consult.analysis.service.domain.SystemAnalysisVocabulary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.consult.analysis.service.vo.SystemAnalysisVocabularyVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author laoA
 * @since 2024-02-23
 */
public interface SystemAnalysisVocabularyMapper extends BaseMapper<SystemAnalysisVocabulary> {
    @Select("select siv.class_id as cid,siv.id as sid,siv.root_name as rootName,sivw.major_name as majorName,sivw.coin,sivw.words from system_information_vocabulary siv \n" +
            "        left join system_information_vocabulary_word sivw on siv.id=sivw.vocabulary_id")
    List<SystemAnalysisVocabularyVO> getList();
}
