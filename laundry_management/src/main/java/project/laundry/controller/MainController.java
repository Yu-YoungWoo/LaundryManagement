package project.laundry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping("/")
    public String mainPage() {

        return "index";
    }

    @GetMapping("/customer-list")
    public String customerListPage() {

        return "/customer_table/tables";
    }

    @GetMapping("/customer-list/add")
    public String customerListAddPage() {

        return "/customer_table/add";
    }



}
