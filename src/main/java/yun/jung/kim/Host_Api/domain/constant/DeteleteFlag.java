package yun.jung.kim.Host_Api.domain.constant;

import lombok.Getter;

public enum DeteleteFlag {
    DETELETE("삭제", true),
    CREATE("생성", false);

    @Getter private final String description;
    @Getter private final Boolean delete;

    DeteleteFlag(String description, Boolean delete) {
        this.description = description;
        this.delete = delete;
    }
}
