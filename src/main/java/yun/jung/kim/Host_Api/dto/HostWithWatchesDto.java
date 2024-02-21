package yun.jung.kim.Host_Api.dto;

import yun.jung.kim.Host_Api.domain.Host;
import yun.jung.kim.Host_Api.domain.constant.DeteleteFlag;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public record HostWithWatchesDto(
        Long id,
        UserAccountDto userAccountDto,
        Set<WatchesDto> watchesDtos,
        String name,
        String ip,
        DeteleteFlag deleteFlag,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) implements Serializable {
    public static HostWithWatchesDto of(Long id, UserAccountDto userAccountDto, Set<WatchesDto> watchesDtos,
                                        String name, String ip, DeteleteFlag deleteFlag,
                                        LocalDateTime createdAt, String createdBy,
                                        LocalDateTime modifiedAt, String modifiedBy){
        return new HostWithWatchesDto(id, userAccountDto, watchesDtos, name, ip, deleteFlag, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static HostWithWatchesDto from(Host entity) {
        return new HostWithWatchesDto(
                entity.getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getWatches().stream().map(WatchesDto::from).collect(Collectors.toCollection(LinkedHashSet::new)),
                entity.getName(),
                entity.getIp(),
                entity.getDeleteFlag(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }
}
