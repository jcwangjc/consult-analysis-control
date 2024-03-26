package com.consult.analysis.calculate.utils;

import com.consult.analysis.calculate.enums.LanguageType;

public class LanguageDetection {
    public static LanguageType check(String text) {
        int chineseCount = countChineseCharacters(text);
        int englishCount = countEnglishCharacters(text);

        double chineseRatio = (double) chineseCount / text.length();
        double englishRatio = (double) englishCount / text.length();

        if (chineseRatio > 0.5) {
            return LanguageType.CHINESE;
        } else if (englishRatio > 0.5) {
            return LanguageType.ENGLISH;
        } else {
            return LanguageType.ENGLISH_CHINESE;
        }
    }

    private static int countChineseCharacters(String text) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 0x4E00 && c <= 0x9FFF) {
                count++;
            }
        }
        return count;
    }

    private static int countEnglishCharacters(String text) {
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
                count++;
            }
        }
        return count;
    }
}
