package io.github.dzmitryrak.enums;

public enum SortOrder {
    DESC("descending"),
    ASC("ascending");

    private String text;

    SortOrder(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}