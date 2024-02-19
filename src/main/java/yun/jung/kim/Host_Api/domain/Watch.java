package yun.jung.kim.Host_Api.domain;

import java.time.LocalDateTime;

public class Watch{
    private long id;

    private Host host;

    private String eventOccurrence;//사건 발생
    private String eventType;//사건 유형
    private  String eventResult;//사건 결과

    private LocalDateTime eventOccurrCreatedAt;//사건 발생 일시
    private String eventOccurrCreatedBy;//사건 발생 주체의 시원

    private LocalDateTime createdAt;//등록 시간
    private String createdBy;//등록자
    private LocalDateTime modifiedAt;//수정 시간
    private String modifiedBy;//수정자
}
