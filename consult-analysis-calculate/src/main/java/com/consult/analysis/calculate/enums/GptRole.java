package com.consult.analysis.calculate.enums;

/**
 * @author : laoa
 * @describe : gpt角色
 * @email : laoa@markcoin.net
 */
public enum GptRole {
    USER("user"),
    SYSTEM("system");
    private String role;

    GptRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
