package com.consult.analysis.service.test.main;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;

import java.util.List;

public class NLPTest3 {
    public static void main(String[] args) {
        // 需要分词的文本
        String text = "这是一段需要根据指定分隔符进行分词的文本，分隔符是逗号,分号;冒号:等。";

        // 指定的分隔符
        String delimiter = "[,;:]";

        // 根据指定分隔符切分文本
        String[] parts = text.split(delimiter);

        // 对每个切分后的子串进行分词
        for (String part : parts) {
            // 进行分词
            List<Term> termList = HanLP.segment(part);

            // 打印分词结果
            System.out.println("分词结果：");
            for (Term term : termList) {
                System.out.println(term.word);
            }
        }
    }
}
