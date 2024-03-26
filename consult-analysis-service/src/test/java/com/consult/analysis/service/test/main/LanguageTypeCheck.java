package com.consult.analysis.service.test.main;

import com.consult.analysis.calculate.enums.LanguageType;
import com.consult.analysis.calculate.utils.LanguageDetection;

public class LanguageTypeCheck {
    public static void main(String[] args) {
        String content="撒大声地所多所btc";
        LanguageType languageType = LanguageDetection.check(content);
        switch (languageType){
            case ENGLISH:
                System.out.println("英语");
                break;
            case CHINESE:
                System.out.println("中文");
                break;
            case ENGLISH_CHINESE:
                System.out.println("混合");
                break;
        }
    }
}
