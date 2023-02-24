package project.laundry.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.laundry.dto.post_dto.postDto;
import project.laundry.service.PostService;

import java.util.List;

@Controller
@RequestMapping("/customer-list")
@RequiredArgsConstructor
@Slf4j
public class CustomerListController {

    private final PostService service;


    @GetMapping
    public String ListPage(Model model) {
        List<postDto> posts = service.findAll();

        model.addAttribute("posts", posts);

        return "/customer_table/tables";
    }

    @GetMapping("/add")
    public String GET_AddPage(Model model) {

        model.addAttribute("post", new postDto());

        return "/customer_table/add";
    }

    @PostMapping("/add")
    public String POST_AddPage(@ModelAttribute("post") @Valid postDto postDto, BindingResult br, RedirectAttributes redirectAttributes, Model model) {

        if(br.hasErrors()) {
            log.error("POST_AddPage BR : {}", br);
            return "/customer_table/add";
        }

        Long postId = service.register(postDto);

        redirectAttributes.addAttribute("postId", postId);
        return "redirect:/customer-list/{postId}/detail";
    }


    @GetMapping("/{postId}/detail")
    public String GET_detail(@PathVariable Long postId, Model model) {
        postDto postDto = service.findOne(postId);

        model.addAttribute("post", postDto);

        return "/customer_table/detail";
    }


    @GetMapping("/{postId}/detail/update")
    public String GET_update(@PathVariable Long postId, Model model) {
        postDto postDto = service.findOne(postId);

        model.addAttribute("post", postDto);

        return "/customer_table/update";
    }


    @PostMapping("/{postId}/detail/update")
    public String POST_update(@ModelAttribute("post") @Valid postDto dto, BindingResult br, @PathVariable Long postId) {

        if(br.hasErrors()) {
            log.error("POST_update BR : {}", br);
            return "/customer_table/update";
        }
        Long updatePostId = service.update(postId, dto);

        return "redirect:/customer-list/{postId}/detail";
    }


    @GetMapping("/{postId}/detail/delete")
    public String DELETE_Post(@PathVariable("postId") Long postId, Model model) {
        service.delete(postId);

        return "redirect:/customer-list";
    }



}
