package yun.jung.kim.Host_Api.domain;

import java.time.LocalDateTime;

public class Host {
    private long id;
    private String name;
    private String ip;

    private LocalDateTime createdAt;//등록 시간
    private String createdBy;//등록자
    private LocalDateTime modifiedAt;//수정 시간
    private String modifiedBy;//수정자
}
