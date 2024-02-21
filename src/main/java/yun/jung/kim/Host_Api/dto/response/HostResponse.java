package yun.jung.kim.Host_Api.dto.response;

import yun.jung.kim.Host_Api.domain.constant.DeteleteFlag;
import yun.jung.kim.Host_Api.dto.HostDto;

import java.time.LocalDateTime;

public record HostResponse(
        Long id,
        String name,
        String ip,
        DeteleteFlag deteleteFlag,
        LocalDateTime createdAt,
        String userId,
        String email)
{
    public static HostResponse of(Long id, String name, String ip, DeteleteFlag deteleteFlag, LocalDateTime createdAt, String userId, String email){
        return new HostResponse(id, name, ip, deteleteFlag, createdAt, userId, email);
    }

    public static HostResponse from(HostDto dto){
        String userId = dto.userAccountDto().userId();
        if(userId == null || userId.isBlank())
            userId = dto.userAccountDto().userId();

        return new HostResponse(
                dto.id(),
                dto.name(),
                dto.ip(),
                dto.deleteFlag(),
                dto.createdAt(),
                dto.userAccountDto().userId(),
                dto.userAccountDto().email()
        );
    }
}
