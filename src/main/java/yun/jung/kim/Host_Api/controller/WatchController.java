package yun.jung.kim.Host_Api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/watches")
@Controller
@RequiredArgsConstructor
public class WatchController {
    @GetMapping
    public String hosts(ModelMap map){
        map.addAttribute("watches", List.of());
        return "watches/index";
    }
}
