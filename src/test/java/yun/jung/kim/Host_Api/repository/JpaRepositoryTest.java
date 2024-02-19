package yun.jung.kim.Host_Api.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import yun.jung.kim.Host_Api.config.JpaConfig;
import yun.jung.kim.Host_Api.domain.Host;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("----- 연결 테스트 -----")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {
    private HostRepository hostRepository;

    public JpaRepositoryTest(@Autowired HostRepository hostRepository) {
        this.hostRepository = hostRepository;
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

    @Disabled
    @DisplayName("등록 테스트")
    @Test
    @Transactional
    void givenTest_whenInsert_thenWork(){
        //given
        long previousCount = hostRepository.count();

        //when
        Host saveHost = Host.of("name", "123.100.003", false);
        System.out.println("save >> "+saveHost);
        hostRepository.save(saveHost);

        //then
        assertThat(hostRepository.count())
                .isEqualTo(previousCount+1);
    }

    @DisplayName("수정 테스트")
    @Test
    @Transactional
    void givenTest_whenUpdate_thenWork(){
        //given
        Host host = hostRepository.findById(1L).orElseThrow();
        Boolean deleteFlag = true;
        host.setDeleteFlag(deleteFlag);

        //when
        Host saveHost = hostRepository.save(host);
        hostRepository.flush();

        //then
        assertThat(saveHost).hasFieldOrPropertyWithValue("deleteFlag", true);
    }

    @Disabled
    @DisplayName("삭제 테스트")
    @Test
    @Transactional
    void givenTest_whenDelete_thenWork(){
        //given
        Host host = hostRepository.findById(1L).orElseThrow();
        long previousHostCount = hostRepository.count();

        //when
        hostRepository.delete(host);

        //then
        assertThat(hostRepository.count()).isEqualTo(previousHostCount-1);
    }
}