package yun.jung.kim.Host_Api.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yun.jung.kim.Host_Api.domain.Host;
import yun.jung.kim.Host_Api.domain.UserAccount;
import yun.jung.kim.Host_Api.domain.constant.SearchType;
import yun.jung.kim.Host_Api.dto.HostDto;
import yun.jung.kim.Host_Api.repository.HostRepository;
import yun.jung.kim.Host_Api.repository.UserAccountRepository;
import yun.jung.kim.Host_Api.repository.WatchRepository;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class HostService {

    private final HostRepository hostRepository;
    private final WatchRepository watchRepository;
    private final UserAccountRepository userAccountRepository;

    @Transactional(readOnly = true)
    public Page<HostDto> searchHosts(SearchType searchType, String searchKeyword, Pageable pageable){
        if(searchKeyword == null || searchKeyword.isBlank()){
            return hostRepository.findAll(pageable).map(HostDto::from);
        }

        return switch (searchType) {
            case NAME -> hostRepository.findByNameContaining(searchKeyword, pageable).map(HostDto::from);
            case IP -> hostRepository.findByIpContaining(searchKeyword, pageable).map(HostDto::from);
            case ID -> hostRepository.findByUserAccount_UserIdContaining(searchKeyword, pageable).map(HostDto::from);
        };
    }

    @Transactional(readOnly = true)
    public HostDto getHost(Long id) {
        return hostRepository.findById(id)
                .map(HostDto::from)
                .orElseThrow(()-> new EntityNotFoundException("호스트 없음 - Host Id "+id));
    }

    public void saveHost(HostDto dto) {
        UserAccount userAccount = userAccountRepository.getReferenceById(dto.userAccountDto().userId());
        hostRepository.save(dto.toEntity(userAccount));
    }

    public void updateHost(Long id, HostDto dto) {
        try {
            Host host = hostRepository.getReferenceById(id);
            UserAccount userAccount = userAccountRepository.getReferenceById(dto.userAccountDto().userId());

            if (host.getUserAccount().equals(userAccount)) {
                if (dto.name() != null) { host.setName(dto.name()); }
                if (dto.ip() != null) { host.setIp(dto.ip()); }
            }
        }catch (EntityNotFoundException e){
            log.warn("호스트 업데이트 실패. 필요한 정보를 찾을 수 없습니다 - {}", e.getLocalizedMessage());
        }
    }

    public void deleteHost(Long id, HostDto dto) {
        try {
            Host host = hostRepository.getReferenceById(id);
            UserAccount userAccount = userAccountRepository.getReferenceById(dto.userAccountDto().userId());

            if (host.getUserAccount().equals(userAccount)) {
                if (dto.deleteFlag() != null) { host.setDeleteFlag(dto.deleteFlag()); }
            }
        }catch (EntityNotFoundException e){
            log.warn("호스트 삭제 실패. 필요한 정보를 찾을 수 없습니다 - {}", e.getLocalizedMessage());
        }
    }
}