package yun.jung.kim.Host_Api.dto.response;

import yun.jung.kim.Host_Api.domain.constant.EventType;
import yun.jung.kim.Host_Api.dto.UserAccountDto;
import yun.jung.kim.Host_Api.dto.WatchesDto;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

public record WatchResponse(
                    Long id,
                    String eventOccurrence,
                    EventType eventType,
                    String eventResult,
                    LocalDateTime createdAt,
                    String userId,
                    String email
    ){
    public static WatchResponse of(Long id, String eventOccurrence, EventType eventType, String eventResult,
                                   LocalDateTime createdAt, String userId, String email){
        return new WatchResponse(id, eventOccurrence, eventType, eventResult, createdAt, userId, email);
    }

    public static WatchResponse from(WatchesDto dto) {
        String userId = dto.userAccountDto().userId();
        if (userId == null || userId.isBlank()) {
            userId = dto.userAccountDto().userId();
        }

        return new WatchResponse(
                dto.id(),
                dto.eventOccurrence(),
                dto.eventType(),
                dto.eventResult(),
                dto.createdAt(),
                dto.userAccountDto().userId(),
                dto.userAccountDto().email()
        );
    }
}
