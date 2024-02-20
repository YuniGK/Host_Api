package yun.jung.kim.Host_Api.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import yun.jung.kim.Host_Api.domain.Host;
import yun.jung.kim.Host_Api.domain.Watch;
import yun.jung.kim.Host_Api.domain.constant.SearchType;
import yun.jung.kim.Host_Api.dto.HostDto;
import yun.jung.kim.Host_Api.dto.WatchesDto;
import yun.jung.kim.Host_Api.repository.HostRepository;
import yun.jung.kim.Host_Api.repository.WatchRepository;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("[SERVICE] - Watches")
@ExtendWith(MockitoExtension.class)
class WatchesServiceTest {
    @InjectMocks
    private WatchesService sut;
    @Mock
    private WatchRepository watchRepository;

    @DisplayName("감시 검색, 감시 리스트 반환")
    @Test
    void givenSearchParametes_whenWatches_thenReturnHostList(){
        //given
        //when
        Page<WatchesDto> watches = sut.searchWatches(SearchType.NAME, "search keyword");

        //then
        assertThat(watches).isNotNull();
    }

    @DisplayName("감시 아이디 조회, 감시 반환")
    @Test
    void givenWatchId_whenWatch_thenReturnWatch(){
        //given
        //when
        WatchesDto watch = sut.searchWatch(1L);

        //then
        assertThat(watch).isNotNull();
    }

    @DisplayName("감시 등록")
    @Test
    void givenInfo_whenSave_thenReturn(){
        //given
        WatchesDto dto = WatchesDto.of("사건 발생", "사건 유형", "사건 결과", "이름", LocalDateTime.now());
        given(watchRepository.save(any(Watch.class))).willReturn(null);

        //when
        sut.saveWatch(dto);

        //then
        then(watchRepository).should().save(any(Watch.class));
    }

    @DisplayName("감시 수정 - 사건 결과")
    @Test
    void givenInfo_whenUpdate_thenReturn(){
        //given
        WatchesDto dto = WatchesDto.of("사건 발생", "사건 유형", "사건 결과", "이름", null);

        given(watchRepository.save(any(Watch.class))).willReturn(null);

        //when
        sut.updateWatch(1L, dto);

        //then
        then(watchRepository).should().save(any(Watch.class));
    }
}