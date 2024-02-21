package yun.jung.kim.Host_Api.dto.request;

import yun.jung.kim.Host_Api.domain.constant.DeteleteFlag;
import yun.jung.kim.Host_Api.dto.HostDto;
import yun.jung.kim.Host_Api.dto.UserAccountDto;

public record HostRequest (String name,
                           String ip,
                           DeteleteFlag deteleteFlag)
{
    public static HostRequest of(String name, String ip){
        return HostRequest.of(name, ip);
    }

    public HostDto toDto(UserAccountDto userAccountDto){
        return HostDto.of(
                name,
                ip,
                deteleteFlag,
                userAccountDto
        );
    }
}
