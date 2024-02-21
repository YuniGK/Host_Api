package yun.jung.kim.Host_Api.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;
import yun.jung.kim.Host_Api.domain.Host;
import yun.jung.kim.Host_Api.domain.UserAccount;
import yun.jung.kim.Host_Api.domain.constant.DeteleteFlag;
import yun.jung.kim.Host_Api.domain.constant.SearchType;
import yun.jung.kim.Host_Api.dto.HostDto;
import yun.jung.kim.Host_Api.dto.UserAccountDto;
import yun.jung.kim.Host_Api.repository.HostRepository;
import yun.jung.kim.Host_Api.repository.UserAccountRepository;
import yun.jung.kim.Host_Api.repository.WatchRepository;

import java.time.LocalDateTime;
import java.util.Optional;

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
    @Mock
    private WatchRepository watchRepository;
    @Mock
    private UserAccountRepository userAccountRepository;

    @DisplayName("호스트 검색 X, 호스트 리스트 반환")
    @Test
    void givenNoSearchParametes_whenHoset_thenReturnHostList(){
        //given
        Pageable pageable = Pageable.ofSize(10);
        given(hostRepository.findAll(pageable)).willReturn(Page.empty());

        //when
        Page<HostDto> hosts = sut.searchHosts(null, null, pageable);

        //then
        assertThat(hosts).isEmpty();
        then(hostRepository).should().findAll(pageable);
    }

    @DisplayName("호스트 검색 O, 호스트 리스트 반환")
    @Test
    void givenSearchParametes_whenHoset_thenReturnHostList(){
        //given
        SearchType searchType = SearchType.IP;
        String searchKeyword = "124.152.67.139";
        Pageable pageable = Pageable.ofSize(10);
        given(hostRepository.findByIpContaining(searchKeyword, pageable)).willReturn(Page.empty());

        //when
        Page<HostDto> hosts = sut.searchHosts(searchType, searchKeyword, pageable);

        //then
        assertThat(hosts).isEmpty();
        then(hostRepository).should().findByIpContaining(searchKeyword, pageable);
    }

    @DisplayName("호스트 단건 조회, 호스트 반환")
    @Test
    void givenHostId_whenHoset_thenReturnHost(){
        //given
        Long hostId = 1L;
        Host host = createHost();
        given(hostRepository.findById(hostId)).willReturn(Optional.of(host));

        // When
        HostDto dto = sut.getHost(hostId);

        //then
        assertThat(dto)
                .hasFieldOrPropertyWithValue("name", host.getName())
                .hasFieldOrPropertyWithValue("ip", host.getIp());

        then(hostRepository).should().findById(hostId);
    }

    @DisplayName("호스트 등록")
    @Test
    void givenInfo_whenSave_thenReturn(){
        //given
        HostDto dto = createHostDto();

        given(userAccountRepository.getReferenceById(dto.userAccountDto().userId())).willReturn(createUserAccount());
        given(hostRepository.save(any(Host.class))).willReturn(createHost());

        //when
        sut.saveHost(dto);

        //then
        then(userAccountRepository).should().getReferenceById(dto.userAccountDto().userId());
        then(hostRepository).should().save(any(Host.class));
    }


    //@DisplayName("관리자만 호스트 등록 가능")

    @DisplayName("호스트 수정 - name, ip")
    @Test
    void givenInfo_whenUpdate_thenReturn(){
        //given
        Host host = createHost();
        HostDto dto = createHostDto();

        given(hostRepository.getReferenceById(dto.id())).willReturn(host);
        given(userAccountRepository.getReferenceById(dto.userAccountDto().userId())).willReturn(dto.userAccountDto().toEntity());
        willDoNothing().given(hostRepository).flush();

        //when
        sut.updateHost(dto.id(), dto);

        //then
        assertThat(host)
                .hasFieldOrPropertyWithValue("name", dto.name())
                .hasFieldOrPropertyWithValue("ip", dto.ip());

        then(hostRepository).should().getReferenceById(dto.id());
        then(userAccountRepository).should().getReferenceById(dto.userAccountDto().userId());
        then(hostRepository).should().flush();
    }

    //@DisplayName("없는 호스트 수정 정보를 입력, 감시 생성")

    @DisplayName("호스트 삭제")
    @Test
    void givenInfo_whenDelete_thenReturn(){
        //given
        Host host = createHost();
        HostDto dto = createHostDto();

        given(hostRepository.getReferenceById(dto.id())).willReturn(host);
        given(userAccountRepository.getReferenceById(dto.userAccountDto().userId())).willReturn(dto.userAccountDto().toEntity());
        willDoNothing().given(hostRepository).flush();

        //when
        sut.deleteHost(dto.id(), dto);

        //then
        assertThat(host)
                .hasFieldOrPropertyWithValue("name", dto.name())
                .hasFieldOrPropertyWithValue("ip", dto.ip())
                .hasFieldOrPropertyWithValue("deteleteFlag", DeteleteFlag.DETELETE);

        then(hostRepository).should().getReferenceById(dto.id());
        then(userAccountRepository).should().getReferenceById(dto.userAccountDto().userId());
        then(hostRepository).should().flush();
    }

    /*--- 임시 데이터 ---*/
    private UserAccount createUserAccount(){
        return createUserAccount("id", "pwd", "e-mail");
    }
    private UserAccount createUserAccount(String uesrId, String userPassword, String email) {
        return UserAccount.of(
                uesrId,
                "yuni_test01 ",
                "yuni_test01@email.com"
        );
    }

    private Host createHost(){
        return createHost(1L);
    };
    private Host createHost(Long id) {
        Host host = Host.of(
                "Home",
                "91.143.91.108",
                DeteleteFlag.CREATE,
                createUserAccount()
        );
        ReflectionTestUtils.setField(host, "id", id);

        return host;
    }

    private HostDto createHostDto() {
        return createHostDto("name", "ip");
    }

    private HostDto createHostDto(String name,String ip) {
        return HostDto.of(
                1L,
                "Home",
                "127.0.0.1",
                DeteleteFlag.CREATE,
                createUserAccountDto(),
                LocalDateTime.now(),
                "yuni",
                LocalDateTime.now(),
                "yuni"
                );
    }

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(
                "yuni",
                "pwd",
                "yuni@email.com",
                LocalDateTime.now(),
                "yuni",
                LocalDateTime.now(),
                "yuni"
        );
    }
}