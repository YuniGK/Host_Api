package yun.jung.kim.Host_Api.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import yun.jung.kim.Host_Api.domain.Host;
import yun.jung.kim.Host_Api.domain.constant.SearchType;
import yun.jung.kim.Host_Api.dto.HostDto;
import yun.jung.kim.Host_Api.repository.HostRepository;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("[SERVICE] - Host")
@ExtendWith(MockitoExtension.class)
class HostServiceTest {
    @InjectMocks
    private HostService sut;
    @Mock
    private HostRepository hostRepository;

    @DisplayName("호스트 검색, 호스트 리스트 반환")
    @Test
    void givenSearchParametes_whenHoset_thenReturnHostList(){
        //given
        //when
        Page<HostDto> hosts = sut.searchHosts(SearchType.NAME, "search keyword");

        //then
        assertThat(hosts).isNotNull();
    }

    @DisplayName("호스트 아이디 조회, 호스트 반환")
    @Test
    void givenHostId_whenHoset_thenReturnHost(){
        //given
        //when
        HostDto host = sut.searchHost(1L);

        //then
        assertThat(host).isNotNull();
    }

    @DisplayName("호스트 등록")
    @Test
    void givenInfo_whenSave_thenReturn(){
        //given
        HostDto dto = HostDto.of("home","127.0.0.1", false, "yuni", LocalDateTime.now());
        given(hostRepository.save(any(Host.class))).willReturn(null);

        //when
        sut.saveHost(dto);

        //then
        then(hostRepository).should().save(any(Host.class));
    }

    @DisplayName("호스트 수정 - name, ip")
    @Test
    void givenInfo_whenUpdate_thenReturn(){
        //given
        HostDto dto = HostDto.of("home", "127.0.0.1", false,"yuni",null);

        given(hostRepository.save(any(Host.class))).willReturn(null);

        //when
        sut.updateHost(1L, dto);

        //then
        then(hostRepository).should().save(any(Host.class));
    }

    @DisplayName("호스트 삭제")
    @Test
    void givenInfo_whenDelete_thenReturn(){
        //given
        HostDto dto = HostDto.of("home", "127.0.0.1", true,"yuni",null);

        given(hostRepository.save(any(Host.class))).willReturn(null);

        //when
        sut.updateDeleteHost(1L, dto);

        //then
        then(hostRepository).should().save(any(Host.class));
    }
}