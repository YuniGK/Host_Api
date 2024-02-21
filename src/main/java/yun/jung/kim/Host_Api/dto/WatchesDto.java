package yun.jung.kim.Host_Api.dto;

import yun.jung.kim.Host_Api.domain.Watch;
import yun.jung.kim.Host_Api.domain.constant.EventType;

import java.io.Serializable;
import java.time.LocalDateTime;

public record WatchesDto(
        Long id,
        UserAccountDto userAccountDto,
        String eventOccurrence,
        EventType eventType,
        String eventResult,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) implements Serializable {
    public static WatchesDto of(Long id, UserAccountDto userAccountDto, String eventOccurrence, EventType eventType, String eventResult) {
        return new WatchesDto(null, userAccountDto, eventOccurrence, eventType, eventResult, null, null, null, null);
    }
    public static WatchesDto of(Long id, UserAccountDto userAccountDto, String eventOccurrence, EventType eventType, String eventResult, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new WatchesDto(id, userAccountDto, eventOccurrence, eventType, eventResult, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static WatchesDto from(Watch entity){
        return new WatchesDto(
                entity.getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getEventOccurrence(),
                entity.getEventType(),
                entity.getEventResult(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

}
