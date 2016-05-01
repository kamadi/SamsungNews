package me.kamadi.samsungnewscrawler.model;

/**
 * Created by Madiyar on 01.05.2016.
 */
public class ApiResponse {
    private String code;
    private String message;
    private Content content;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
