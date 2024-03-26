package com.consult.analysis.calculate.enums;

public enum LanguageJoin {
    TITLE_JOIN(" posted a consultation!"," 发表咨询！");
    private String english;
    private String chinese;

    LanguageJoin(String english, String chinese) {
        this.english = english;
        this.chinese = chinese;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }
}
