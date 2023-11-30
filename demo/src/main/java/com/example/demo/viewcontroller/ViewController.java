package com.example.demo.viewcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping("/jsp")
    public String jsp() throws Exception {
        return "main";
    }
}
