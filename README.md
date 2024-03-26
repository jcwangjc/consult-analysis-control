# OPEN AI区块链资讯情感分析

上周末，用两天时间给初中同学写了一个基于OPEN AI的区块链情感分析插件，感觉gpt还是很吊的，尤其是智能图片生成，感觉确实非常牛。

血赚一条芙蓉王的同时，也深感AI发展的迅捷，隐隐竟有些焦虑了，哎！

---

## 需求

输入一条区块链的行业咨询数据,经过gpt的分析以后，将符合条件的数据发往下游。

```
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

    //下面这些字段是分析后的结果
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
```