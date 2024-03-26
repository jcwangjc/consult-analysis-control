package com.consult.analysis.calculate.response;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author : laoa
 * @describe : gpt返回消息体
 * @email : laoa@markcoin.net
 */
public class ChatGPTResponse {
    private String id;
    private String object;
    private long created;
    private String model;
    private Choice[] choices;
    private Usage usage;
    @JsonProperty("system_fingerprint")
    private String systemFingerprint;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("object")
    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    @JsonProperty("created")
    public long getCreated() {
        return created;
    }

    public void setCreated(long created) {
        this.created = created;
    }

    @JsonProperty("model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @JsonProperty("choices")
    public Choice[] getChoices() {
        return choices;
    }

    public void setChoices(Choice[] choices) {
        this.choices = choices;
    }

    @JsonProperty("usage")
    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    @JsonProperty("system_fingerprint")
    public String getSystemFingerprint() {
        return systemFingerprint;
    }

    public void setSystemFingerprint(String systemFingerprint) {
        this.systemFingerprint = systemFingerprint;
    }

    public static class Choice {
        private int index;
        private Message message;
        private String[] logprobs;
        @JsonProperty("finish_reason")
        private String finishReason;

        @JsonProperty("index")
        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        @JsonProperty("message")
        public Message getMessage() {
            return message;
        }

        public void setMessage(Message message) {
            this.message = message;
        }

        @JsonProperty("logprobs")
        public String[] getLogprobs() {
            return logprobs;
        }

        public void setLogprobs(String[] logprobs) {
            this.logprobs = logprobs;
        }

        @JsonProperty("finish_reason")
        public String getFinishReason() {
            return finishReason;
        }

        public void setFinishReason(String finishReason) {
            this.finishReason = finishReason;
        }
    }

    public static class Message {
        private String role;
        private String content;

        @JsonProperty("role")
        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        @JsonProperty("content")
        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class Usage {
        @JsonProperty("prompt_tokens")
        private int promptTokens;
        @JsonProperty("completion_tokens")
        private int completionTokens;
        @JsonProperty("total_tokens")
        private int totalTokens;

        public int getPromptTokens() {
            return promptTokens;
        }

        public void setPromptTokens(int promptTokens) {
            this.promptTokens = promptTokens;
        }

        public int getCompletionTokens() {
            return completionTokens;
        }

        public void setCompletionTokens(int completionTokens) {
            this.completionTokens = completionTokens;
        }

        public int getTotalTokens() {
            return totalTokens;
        }

        public void setTotalTokens(int totalTokens) {
            this.totalTokens = totalTokens;
        }
    }
}

