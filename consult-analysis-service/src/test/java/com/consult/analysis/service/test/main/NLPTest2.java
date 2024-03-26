package com.consult.analysis.service.test.main;

import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.summary.TextRankKeyword;

import java.util.List;

public class NLPTest2 {
    public static void main(String[] args) {
        CustomDictionary.add("以太坊");
        CustomDictionary.add("区块链");
        CustomDictionary.add("layer2");
        String text = "is the that the  and  solana will launch layer2 solution next month btc eth solana 区块链 以太坊";

        // 使用 TextRank 算法提取关键词
        List<String> keywords = TextRankKeyword.getKeywordList(text,text.length());

        // 打印关键词
        System.out.println("关键词：");
        for (String keyword : keywords) {
            System.out.println(keyword);
        }


    }
}
