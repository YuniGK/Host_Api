package yun.jung.kim.Host_Api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/hosts")
@Controller
@RequiredArgsConstructor
public class HostController {
    @GetMapping
    public String hosts(ModelMap map){
        map.addAttribute("hosts", List.of());
        return "hosts/index";
    }

    @GetMapping("/{hostId}")
    public String host(@PathVariable Long hostId,
                       ModelMap map){
        map.addAttribute("host", List.of());
        return "hosts/index";
    }

    @GetMapping("/search")
    public String searchHost(ModelMap map){
        map.addAttribute("hosts", List.of());
        return "hosts/index";
    }

    @GetMapping("/search-ip")
    public String searchIpHost(ModelMap map){
        map.addAttribute("hosts", List.of());
        return "hosts/index";
    }
}
