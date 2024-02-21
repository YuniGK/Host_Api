package yun.jung.kim.Host_Api.domain.constant;

import lombok.Getter;
import lombok.Setter;

public enum EventType {
    LOGIN("로그인"),
    LOGOUT("로그아웃"),
    LOGIN_FAIL("로그인 실패"),
    ETC("기타");

    @Setter
    private final String description;

    EventType(String description) {
        this.description = description;
    }
}
