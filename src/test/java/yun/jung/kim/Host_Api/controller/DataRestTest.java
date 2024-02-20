package yun.jung.kim.Host_Api.controller;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Stack;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
@DisplayName("Data Rest - API 테스트")
public class DataRestTest {
    private final MockMvc mvc;
    public DataRestTest(@Autowired MockMvc mvc){
        this.mvc = mvc;
    }

    @Test
    @DisplayName("[api] 게시글 리스트 조회")
    void given_when_thenHostsList() throws Exception{
        //given

        //when & then
        mvc.perform(get("/api/hosts"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("[api] 게시글 단건 조회")
    void given_when_thenHostList() throws Exception{
        //given

        //when & then
        mvc.perform(get("/api/hosts/1"))
                .andExpect(status().isOk());
    }
}
