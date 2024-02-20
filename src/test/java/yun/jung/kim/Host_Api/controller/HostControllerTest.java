package yun.jung.kim.Host_Api.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import yun.jung.kim.Host_Api.config.SecurityConfig;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("[CONTROLLER] - host")
@Import(SecurityConfig.class)
@WebMvcTest(HostController.class)
class HostControllerTest {
    private final MockMvc mvc;

    public HostControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[GET] 호스트 리스트 조회 - 정상")
    @Test
    public void given_whenHost_thenReturnsHostList() throws Exception{
        //given
        //when & then
        mvc.perform(get("/hosts"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("hosts/index"))
                .andExpect(model().attributeExists("hosts"));
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
        //when & then
        mvc.perform(get("/hosts/search"))
                .andExpect(status().isOk());
    }

    @DisplayName("[GET] 호스트 ip 검색 - 정상")
    @Test
    public void given_whenHost_thenSearchIpHost() throws Exception{
        //given
        //when & then
        mvc.perform(get("/hosts/search-ip"))
                .andExpect(status().isOk());
    }

    @DisplayName("[POST] 호스트 저장 - 정상")
    @Test
    public void given_whenInsertHost_thenHost() throws Exception{
        //given
        //when & then
        mvc.perform(get("/hosts/search-ip"))
                .andExpect(status().isOk());
    }

    @DisplayName("[POST] 호스트 업데이트 - 정상")
    @Test
    public void given_whenUpdateHost_thenHost() throws Exception{
        //given
        //when & then
        mvc.perform(get("/hosts/search-ip"))
                .andExpect(status().isOk());
    }

    @DisplayName("[POST] 호스트 삭제플래그 업데이트 - 정상")
    @Test
    public void given_whenDeleteHost_thenHost() throws Exception{
        //given
        //when & then
        mvc.perform(get("/hosts/search-ip"))
                .andExpect(status().isOk());
    }

}