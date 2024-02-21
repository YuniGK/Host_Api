package yun.jung.kim.Host_Api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import yun.jung.kim.Host_Api.domain.constant.SearchType;
import yun.jung.kim.Host_Api.dto.request.HostRequest;
import yun.jung.kim.Host_Api.dto.response.HostResponse;
import yun.jung.kim.Host_Api.dto.security.BoardPrincipal;
import yun.jung.kim.Host_Api.service.HostService;
import yun.jung.kim.Host_Api.service.PaginationService;

import java.util.List;

@RequestMapping("/hosts")
@Controller
@RequiredArgsConstructor
public class HostController {

    private final HostService hostService;
    private final PaginationService paginationService;

    @GetMapping
    public String hosts(@RequestParam(required = false) SearchType searchType,
                        @RequestParam(required = false) String searchValue,
                        @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
                        ModelMap map){

        Page<HostResponse> hosts = hostService.searchHosts(searchType, searchValue, pageable).map(HostResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), hosts.getTotalPages());

        map.addAttribute("hosts", hosts);
        map.addAttribute("paginationBarNumbers", barNumbers);
        map.addAttribute("searchTypes", SearchType.values());

        return "hosts/index";
    }

    @GetMapping("/{hostId}")
    public String host(@PathVariable Long hostId,
                       ModelMap map){
        HostResponse host = HostResponse.from(hostService.getHost(hostId));

        map.addAttribute("host", host);
        map.addAttribute("totalCount", host.getHostCount());

        return "hosts/index";
    }

    @GetMapping("/search")
    public String searchHost(
            @RequestParam(required = false) String searchValue,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            ModelMap map){
        Page<HostResponse> hosts = hostService.searchHosts(searchValue, pageable).map(HostResponse::from);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), hosts.getTotalPages());
        List<String> hashtags = hostService.getHost();

        map.addAttribute("hosts", hosts);
        map.addAttribute("paginationBarNumbers", barNumbers);

        return "hosts/index";
    }

    @GetMapping("/form")
    public String articleForm(ModelMap map) {
        map.addAttribute("formStatus", FormStatus.CREATE);

        return "hosts/index";
    }

    @PostMapping("/form")
    public String postNewHost(
            @AuthenticationPrincipal BoardPrincipal boardPrincipal,
            HostRequest hostRequest
    ) {
        hostService.saveHost(HostRequest.toDto(boardPrincipal.toDto()));

        return "redirect:/hosts";
    }

    @GetMapping("/{hostId}/form")
    public String updateHostForm(@PathVariable Long hostId, ModelMap map) {
        HostRequest host = HostResponse.from(hostService.getHost(hostId));

        map.addAttribute("host", host);
        map.addAttribute("formStatus", FormStatus.UPDATE);

        return "hosts/index";
    }

    @PostMapping("/{hostId}/form")
    public String updateHost(
            @PathVariable Long hostId,
            @AuthenticationPrincipal BoardPrincipal boardPrincipal,
            HostRequest hostRequest
    ) {
        hostService.updateHost(hostId, hostRequest.toDto(boardPrincipal.toDto()));

        return "redirect:/hosts/" + hostId;
    }

    @PostMapping("/{hostId}/delete")
    public String deleteHost(
            @PathVariable Long hostId,
            @AuthenticationPrincipal BoardPrincipal boardPrincipal
    ) {
        hostService.deleteHost(hostId, hostRequest.toDto(boardPrincipal.toDto()));

        return "redirect:/hosts";
    }
}
