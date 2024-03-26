package com.consult.analysis.service.test.main;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.seg.common.Term;

import java.util.List;

public class NLPTest {
    public static void main(String[] args) {
        // 添加自定义词汇
        CustomDictionary.add("区块链");
        CustomDictionary.add("比特币");

        String text = "solana will launch layer2 solution next month!！区块链比特币";

        // 使用 HanLP 进行分词
        List<Term> termList = HanLP.segment(text);

        // 打印分词结果
        for (Term term : termList) {
            System.out.println("===1");
            System.out.println(term.word.trim());
            System.out.println("===2");
        }
    }
}
