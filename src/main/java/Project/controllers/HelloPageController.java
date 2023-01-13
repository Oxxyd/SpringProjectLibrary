package Project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloPageController {

    @GetMapping("/")
    public String helloPage(){
        return "redirect";
    }
}
