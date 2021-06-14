package io.pivotal.pal.tracker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelomeController {

    @GetMapping("/")
    public String sayHello() {
        return "hello";
    }
}
