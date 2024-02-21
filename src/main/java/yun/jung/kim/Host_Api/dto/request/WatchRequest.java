package yun.jung.kim.Host_Api.dto.request;

import yun.jung.kim.Host_Api.domain.constant.EventType;
import yun.jung.kim.Host_Api.dto.UserAccountDto;
import yun.jung.kim.Host_Api.dto.WatchesDto;

public record WatchRequest (
                    Long hostId,
                    String eventOccurrence,
                    EventType eventType,
                    String eventResult){
    public static WatchRequest of(Long hostId, String eventOccurrence, EventType eventType, String eventResult){
        return new WatchRequest(hostId, eventOccurrence, eventType, eventResult);
    }

    public WatchesDto toDto(UserAccountDto userAccountDto){
        return WatchesDto.of(
                hostId,
                userAccountDto,
                eventOccurrence,
                eventType,
                eventResult
        );
    }

}
