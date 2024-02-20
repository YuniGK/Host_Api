package yun.jung.kim.Host_Api.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public record WatchesDto(
        String eventOccurrence,
        String eventType,
        String eventResult,
        String createdBy,
        LocalDateTime createdAt
) implements Serializable {
    public static WatchesDto of(String eventOccurrence, String eventType, String eventResult, String createdBy, LocalDateTime createdAt) {
        return new WatchesDto(eventOccurrence, eventType, eventResult, createdBy, createdAt);
    }
}
