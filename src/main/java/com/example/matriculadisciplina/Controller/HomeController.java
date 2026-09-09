package com.example.matriculadisciplina.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
        //return "login";
        return "formLogin";
    }

    @GetMapping("/AreaAdmin")
    public String areaAdmin(){
        return "/pagAdmin";
    }
}
