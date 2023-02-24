package project.laundry.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import project.laundry.dto.post_dto.postDto;
import project.laundry.service.PostService;
import project.laundry.service.VisitService;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final VisitService service;
    private final PostService postService;

    public void init() {
        postDto dto = postDto.builder()
                .name("홍길동")
                .phone("010-1234-5678")
                .clothCount(4)
                .price(12324)
                .build();

        postDto dto2 = postDto.builder()
                .name("아무개")
                .phone("010-1234-3212")
                .clothCount(3)
                .price(13000)
                .build();

        postService.register(dto);
        postService.register(dto2);
    }

    @GetMapping
    public String mainPage(Model model) {


        Long visitsInMonth = service.getVisitsInMonth();
        Long revenueInMonth = service.getRevenueInMonth();

        Long visitsInYear = service.getVisitsInYear();
        Long revenueInYear = service.getRevenueInYear();

        List<Integer> list = service.getTotalRevenueByMonth();

        model.addAttribute("visitsInMonth", visitsInMonth);
        model.addAttribute("revenueInMonth", revenueInMonth);
        model.addAttribute("visitsInYear", visitsInYear);
        model.addAttribute("revenueInYear", revenueInYear);

        model.addAttribute("list", list);

        return "index";
    }
}
