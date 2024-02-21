package yun.jung.kim.Host_Api.dto;

import yun.jung.kim.Host_Api.domain.Host;
import yun.jung.kim.Host_Api.domain.UserAccount;
import yun.jung.kim.Host_Api.domain.constant.DeteleteFlag;

import java.io.Serializable;
import java.time.LocalDateTime;

public record HostDto(
        Long id,
        String name,
        String ip,
        DeteleteFlag deleteFlag,
        UserAccountDto userAccountDto,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) implements Serializable {
    public static HostDto of(String name, String ip, DeteleteFlag deleteFlag, UserAccountDto userAccountDto){
        return new HostDto(null, name, ip, deleteFlag, userAccountDto,null, null, null, null);
    }
    public static HostDto of(Long id, String name, String ip, DeteleteFlag deleteFlag, UserAccountDto userAccountDto, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy){
        return new HostDto(id, name, ip, deleteFlag, userAccountDto, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static HostDto from(Host entity) {
        return new HostDto(
                entity.getId(),
                entity.getName(),
                entity.getIp(),
                entity.getDeleteFlag(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public Host toEntity(UserAccount userAccount) {
        return Host.of(
                name,
                ip,
                deleteFlag,
                userAccount
        );
    }
}
