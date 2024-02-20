package yun.jung.kim.Host_Api.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public record HostDto(
        String name,
        String ip,
        Boolean deleteFlag,
        String createdBy,
        LocalDateTime createdAt
) implements Serializable {
    public static HostDto of(String name, String ip, Boolean deleteFlag, String createdBy, LocalDateTime createdAt){
        return new HostDto(name, ip, deleteFlag, createdBy, createdAt);
    }
}
