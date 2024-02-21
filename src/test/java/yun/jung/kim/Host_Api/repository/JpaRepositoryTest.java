package yun.jung.kim.Host_Api.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import yun.jung.kim.Host_Api.config.JpaConfig;
import yun.jung.kim.Host_Api.domain.Host;
import yun.jung.kim.Host_Api.domain.UserAccount;
import yun.jung.kim.Host_Api.domain.constant.DeteleteFlag;
import yun.jung.kim.Host_Api.dto.UserAccountDto;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("----- 연결 테스트 -----")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {
    private HostRepository hostRepository;
    private WatchRepository watchRepository;
    private UserAccountRepository userAccountRepository;

    public JpaRepositoryTest(@Autowired HostRepository hostRepository,
                             @Autowired WatchRepository watchRepository,
                             @Autowired UserAccountRepository userAccountRepository) {
        this.hostRepository = hostRepository;
        this.watchRepository = watchRepository;
        this.userAccountRepository = userAccountRepository;
    }

    @DisplayName("조회 테스트")
    @Test
    void givenTest_whenSelect_thenWork(){
        //given
        //when
        List<Host> hosts = hostRepository.findAll();

        //then
        assertThat(hosts)
                .isNotNull()
                .hasSize(5);
    }
     
    @DisplayName("등록 테스트")
    @Test
    void givenTest_whenInsert_thenWork() throws Exception{
        //given
        long previousCount = hostRepository.count();
        UserAccount userAccount = userAccountRepository.save(createUserAccount());

        //when
        Host saveHost = Host.of("name", "123.100.003", DeteleteFlag.DETELETE, userAccount);
        hostRepository.save(saveHost);

        //then
        assertThat(hostRepository.count())
                .isEqualTo(previousCount+1);
    }

    @DisplayName("수정 테스트")
    @Test
    void givenTest_whenUpdate_thenWork(){
        //given
        Host host = hostRepository.findById(1L).orElseThrow();
        host.setDeleteFlag(DeteleteFlag.DETELETE);

        //when
        Host saveHost = hostRepository.save(host);
        hostRepository.flush();

        //then
        assertThat(saveHost).hasFieldOrPropertyWithValue("deleteFlag", DeteleteFlag.DETELETE);
    }

    @Disabled
    @DisplayName("삭제 테스트")
    @Test
    void givenTest_whenDelete_thenWork(){
        //given
        Host host = hostRepository.findById(1L).orElseThrow();
        long previousHostCount = hostRepository.count();

        //when
        hostRepository.delete(host);

        //then
        assertThat(hostRepository.count()).isEqualTo(previousHostCount-1);
    }

    private UserAccount createUserAccount() {
        return UserAccount.of(
                "yuni_tes01",
                "pwd",
                "yuni_test01@email.com"
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