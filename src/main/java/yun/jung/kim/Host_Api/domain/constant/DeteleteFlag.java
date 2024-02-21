package yun.jung.kim.Host_Api.domain.constant;

import lombok.Getter;
import lombok.Setter;

public enum DeteleteFlag {
    DETELETE("삭제", true),
    CREATE("생성", false);

    @Setter
    private final String description;
    @Setter
    private final Boolean delete;

    DeteleteFlag(String description, Boolean delete) {
        this.description = description;
        this.delete = delete;
    }
}
