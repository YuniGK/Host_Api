package yun.jung.kim.Host_Api.domain.constant;

import lombok.Setter;

public enum SearchType {
    NAME("유저명"), IP("아이피"), ID("유저 ID");

    @Setter private final String description;

    SearchType(String description) {
        this.description = description;
    }
}
