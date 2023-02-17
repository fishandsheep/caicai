package org.fisheep.chat.entity;

public class GptResponse {
    private String text;
    private String choices;
    private String completions;

    // getters and setters


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getChoices() {
        return choices;
    }

    public void setChoices(String choices) {
        this.choices = choices;
    }

    public String getCompletions() {
        return completions;
    }

    public void setCompletions(String completions) {
        this.completions = completions;
    }
}
