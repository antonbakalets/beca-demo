package bh.beca.demo.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class WebController {

    @GetMapping("/")
    public String index() {
        return "forward:/index.html"; // Serves index.html from static folder
    }
}
