package com.consult.analysis.service.event;

import com.consult.analysis.service.enums.AnalyzeType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author : laoa
 * @describe : 输入输出结果
 * @email : laoa@markcoin.net
 */
@Data
@Builder
public class EventMessage {
    //主键
    private Long id;
    //商户ID
    private Long dataSourceId;
    //采集平台的数据主键
    private String textId;
    //采集平台的资讯类型
    private String textType;
    //标题
    private String title;
    //文本内容
    private String content;
    //富文本内容
    private String text;
    //语言
    private String lang;
    //图片 url
    private String img;
    //栏目
    private String catalog;
    //栏目ICON
    private String catalogIcon;
    //是否重要
    private Integer isImportant;
    //重要标签 ,分割
    private String labels;
    //标签 ,分割
    private String tags;
    //关联链接
    private String relateLink;
    //原文链接
    private String sourceLink;
    //发布时间
    private Long publishTime;
    //作者
    private String author;
    //创建时间
    private Date createdByTime;
    //修改时间
    private Date updatedByTime;
    //是否删除 2未删除 1 已删除
    private Integer isDelete;
    //分析类型 INFORMATION-资讯分析 SUBSTANTIVE-名词解析  TRANSLATE-翻译
    private AnalyzeType analyzeType;

    //system message
    private String systemMessage;
    //system translate message
    private String systemTranslateMessage;
    //gpt相关性检查 true为相关 false为不相关
    private Boolean related;
    //gpt评论结果的情绪
    private String mood;
    //gpt评论
    private String analysis;
    //gpt评论多语言版本
    private String analysisMulti;
    //匹配到那些类型的code
    private String codes;
    //匹配到那些币种
    private String coins;

    public EventMessage(Long id, Long dataSourceId, String textId, String textType, String title, String content, String text, String lang, String img, String catalog, String catalogIcon, Integer isImportant, String labels, String tags, String relateLink, String sourceLink, Long publishTime, String author, Date createdByTime, Date updatedByTime, Integer isDelete, AnalyzeType analyzeType, String systemMessage, String systemTranslateMessage, Boolean related, String mood, String analysis, String analysisMulti, String codes, String coins) {
        this.id = id;
        this.dataSourceId = dataSourceId;
        this.textId = textId;
        this.textType = textType;
        this.title = title;
        this.content = content;
        this.text = text;
        this.lang = lang;
        this.img = img;
        this.catalog = catalog;
        this.catalogIcon = catalogIcon;
        this.isImportant = isImportant;
        this.labels = labels;
        this.tags = tags;
        this.relateLink = relateLink;
        this.sourceLink = sourceLink;
        this.publishTime = publishTime;
        this.author = author;
        this.createdByTime = createdByTime;
        this.updatedByTime = updatedByTime;
        this.isDelete = isDelete;
        this.analyzeType = analyzeType;
        this.systemMessage = systemMessage;
        this.systemTranslateMessage = systemTranslateMessage;
        this.related = related;
        this.mood = mood;
        this.analysis = analysis;
        this.analysisMulti = analysisMulti;
        this.codes = codes;
        this.coins = coins;
    }
}
