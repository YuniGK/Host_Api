package yun.jung.kim.Host_Api.controller;

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

@DisplayName("[CONTROLLER] - watches")
@Import(SecurityConfig.class)
@WebMvcTest(WatchesControllerTest.class)
class WatchesControllerTest {
    private final MockMvc mvc;

    public WatchesControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }
}