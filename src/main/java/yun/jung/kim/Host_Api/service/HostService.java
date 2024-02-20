package yun.jung.kim.Host_Api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yun.jung.kim.Host_Api.domain.constant.SearchType;
import yun.jung.kim.Host_Api.dto.HostDto;
import yun.jung.kim.Host_Api.repository.HostRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class HostService {
    private final HostRepository hostRepository;

    @Transactional(readOnly = true)
    public Page<HostDto> searchHosts(SearchType type, String search_keyword){
        return Page.empty();
    }

    @Transactional(readOnly = true)
    public HostDto searchHost(long id) {
        return null;
    }

    public void saveHost(HostDto dto) {
    }

    public void updateHost(long id, HostDto dto) {
    }

    public void updateDeleteHost(long id, HostDto dto) {
    }


}