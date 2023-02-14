package project.laundry.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.laundry.dto.post_dto.postDto;
import project.laundry.service.PostService;

import java.util.List;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class MainController {

    private final PostService service;

    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

    @GetMapping("/customer-list")
    public String ListPage(Model model) {
        service.init();
        List<postDto> posts = service.findAll();

        model.addAttribute("posts", posts);

        return "/customer_table/tables";
    }

    @GetMapping("/customer-list/add")
    public String GET_AddPage(Model model) {

        model.addAttribute("postForm", new postDto());

        return "/customer_table/add";
    }

    @PostMapping("/customer-list/add")
    public String POST_AddPage(@ModelAttribute("postForm") @Valid postDto postDto, BindingResult br, RedirectAttributes redirectAttributes, Model model) {

        if(br.hasErrors()) {
            log.info("br : {}", br);
            return "/customer_table/add";
        }

        Long postId = service.register(postDto);

        redirectAttributes.addAttribute("postId", postId);
        return "redirect:/customer-list/{postId}/detail";
    }


    @GetMapping("/customer-list/{postId}/detail")
    public String GET_detail(@PathVariable Long postId, Model model) {
        postDto postDto = service.findOne(postId);

        model.addAttribute("post", postDto);

        return "/customer_table/detail";
    }


}
