package yun.jung.kim.Host_Api.controller;

import yun.jung.kim.Host_Api.util.FormDataEncoder;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import yun.jung.kim.Host_Api.config.SecurityConfig;
import yun.jung.kim.Host_Api.domain.Host;
import yun.jung.kim.Host_Api.domain.UserAccount;
import yun.jung.kim.Host_Api.domain.constant.DeteleteFlag;
import yun.jung.kim.Host_Api.domain.constant.SearchType;
import yun.jung.kim.Host_Api.dto.HostDto;
import yun.jung.kim.Host_Api.dto.UserAccountDto;
import yun.jung.kim.Host_Api.dto.request.HostRequest;
import yun.jung.kim.Host_Api.dto.response.HostResponse;
import yun.jung.kim.Host_Api.service.HostService;
import yun.jung.kim.Host_Api.service.PaginationService;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("[CONTROLLER] - host")
@Import({SecurityConfig.class, FormDataEncoder.class})
@WebMvcTest(HostController.class)
class HostControllerTest {
    private final MockMvc mvc;
    private final FormDataEncoder formDataEncoder;

    @MockBean private HostService hostService;
    @MockBean private PaginationService paginationService;

    public HostControllerTest(@Autowired MockMvc mvc, @Autowired FormDataEncoder formDataEncoder) {
        this.mvc = mvc;
        this.formDataEncoder = formDataEncoder;
    }

    @DisplayName("[GET] 호스트 리스트 조회 - 정상")
    @Test
    public void given_whenHost_thenReturnsHostList() throws Exception{
        //given
        given(hostService.searchHosts(eq(null), eq(null), any(Pageable.class))).willReturn(Page.empty());
        given(paginationService.getPaginationBarNumbers(anyInt(), anyInt())).willReturn(List.of(0, 1, 2, 3, 4));

        //when & then
        mvc.perform(get("/hosts"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("hosts/index"))
                .andExpect(model().attributeExists("hosts"))
                .andExpect(model().attributeExists("paginationBarNumbers"))
                .andExpect(model().attributeExists("searchTypes"));

        then(hostService).should().searchHosts(eq(null), eq(null), any(Pageable.class));
        then(paginationService).should().getPaginationBarNumbers(anyInt(), anyInt());
    }

    @DisplayName("[GET] 호스트 단건 조회 - 정상")
    @Test
    public void given_whenHost_thenReturnsHost() throws Exception{
        //given
        //when & then
        mvc.perform(get("/hosts/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("hosts/index"))
                .andExpect(model().attributeExists("host"));
    }

    @DisplayName("[GET] 호스트 검색 - 정상")
    @Test
    public void given_whenHost_thenSearchHost() throws Exception{
        //given
        SearchType searchType = SearchType.IP;
        String searchValue = "124.152.67.139";
        given(hostService.searchHosts(eq(searchType), eq(searchValue), any(Pageable.class))).willReturn(Page.empty());
        given(paginationService.getPaginationBarNumbers(anyInt(), anyInt())).willReturn(List.of(0, 1, 2, 3, 4));

        //when & then
        mvc.perform(
                        get("/hosts")
                                .queryParam("searchType", searchType.name())
                                .queryParam("searchValue", searchValue)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("hosts/index"))
                .andExpect(model().attributeExists("hosts"))
                .andExpect(model().attributeExists("searchTypes"));

        then(hostService).should().searchHosts(eq(searchType), eq(searchValue), any(Pageable.class));
        then(paginationService).should().getPaginationBarNumbers(anyInt(), anyInt());
    }

    @DisplayName("[GET] 인증 X - 로그인 페이지로 이동")
    @Test
    void givenNothing_when_thenRedirectsLoginPage() throws Exception {
        // Given
        long articleId = 1L;

        // When & Then
        mvc.perform(get("/hosts/" + articleId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
        then(hostService).shouldHaveNoInteractions();
        then(hostService).shouldHaveNoInteractions();
    }

    @WithMockUser
    @DisplayName("[GET] 새 게시글 작성 페이지")
    @Test
    void givenNothing_whenRequesting_thenReturnsNew() throws Exception {
        // Given

        // When & Then
        mvc.perform(get("/hosts/form"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("hosts/form"))
                .andExpect(model().attribute("formStatus", FormStatus.CREATE));
    }

    @WithUserDetails(value = "yuni", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @DisplayName("[POST] 새 게시글 등록 - 정상")
    @Test
    void givenNewInfo_whenRequesting_thenSave() throws Exception {
        // Given
        HostRequest hostRequest = hostRequest.of("new title", "new content");
        willDoNothing().given(hostService).saveHost(any(HostDto.class));

        // When & Then
        mvc.perform(
                        post("/hosts/form")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                //.content(formDataEncoder.encode(hostRequest))
                                .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/hosts"))
                .andExpect(redirectedUrl("/hosts"));
        then(hostService).should().saveHost(any(HostDto.class));
    }

    @DisplayName("[GET] 게시글 수정 페이지 - 인증 없을 땐 로그인 페이지로 이동")
    @Test
    void givenNothing_whenRequesting_thenRedirectsToLoginPage() throws Exception {
        // Given
        Long hostsId = 1L;

        // When & Then
        mvc.perform(get("/hosts/" + hostsId + "/form"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
        then(hostService).shouldHaveNoInteractions();
    }

    @WithMockUser
    @DisplayName("[GET] 게시글 수정 페이지 - 정상 호출, 인증된 사용자")
    @Test
    void givenAuthorizedUser_whenRequesting_thenReturnsUpdated() throws Exception {
        // Given
        long hostsId = 1L;
        HostDto dto = createHostDto();
        given(hostService.getHost(hostsId)).willReturn(dto);

        // When & Then
        mvc.perform(get("/hosts/" + hostsId + "/form"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("hosts/form"))
                .andExpect(model().attribute("hosts", HostResponse.from(dto)))
                .andExpect(model().attribute("formStatus", FormStatus.UPDATE));
        then(hostService).should().getHost(hostsId);
    }

    @WithUserDetails(value = "yuni", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @DisplayName("[POST] 게시글 수정 - 정상 호출")
    @Test
    void givenUpdatedInfo_whenRequesting_thenUpdatesNew() throws Exception {
        // Given
        Long hostsId = 1L;
        HostRequest hostRequest = HostRequest.of("new name", "new ip");
        willDoNothing().given(hostService).updateHost(eq(hostsId), any(HostDto.class));

        // When & Then
        mvc.perform(
                        post("/hosts/" + hostsId + "/form")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .content(formDataEncoder.encode(hostRequest))
                                .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/hosts/" + hostsId))
                .andExpect(redirectedUrl("/hosts/" + hostsId));
        then(hostService).should().updateHost(eq(hostsId), any(HostDto.class));
    }

    @WithUserDetails(value = "yuni", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @DisplayName("[POST] 게시글 삭제 - 정상 호출")
    @Test
    void givenArticleIdToDelete_whenRequesting_thenDelete() throws Exception {
        // Given
        Long hostsId = 1L;
        HostRequest hostRequest = HostRequest.of("new name", "new ip");
        willDoNothing().given(hostService).updateHost(eq(hostsId), any(HostDto.class));

        // When & Then
        mvc.perform(
                        post("/hosts/" + hostsId + "/delete")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .content(formDataEncoder.encode(hostRequest))
                                .with(csrf())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/hosts/" + hostsId))
                .andExpect(redirectedUrl("/hosts/" + hostsId));
        then(hostService).should().updateHost(eq(hostsId), any(HostDto.class));
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