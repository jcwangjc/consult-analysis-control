package com.consult.analysis.service.enums;

/**
 * @author : laoa
 * @describe : 是否关联
 * @email : laoa@markcoin.net
 */
public enum RelatedResult {
    YES(true),
    NO(false);

    private boolean result;

    RelatedResult(boolean result) {
        this.result=result;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

}
