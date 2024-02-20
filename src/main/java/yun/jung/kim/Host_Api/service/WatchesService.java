package yun.jung.kim.Host_Api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yun.jung.kim.Host_Api.domain.constant.SearchType;
import yun.jung.kim.Host_Api.dto.HostDto;
import yun.jung.kim.Host_Api.dto.WatchesDto;
import yun.jung.kim.Host_Api.repository.HostRepository;
import yun.jung.kim.Host_Api.repository.WatchRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class WatchesService {
    private final WatchRepository watchRepository;

    public Page<WatchesDto> searchWatches(SearchType searchType, String searchKeyword) {
    }

    public WatchesDto searchWatch(long id) {
    }

    public void saveWatch(WatchesDto dto) {
    }

    public void updateWatch(long l, WatchesDto dto) {
    }
}