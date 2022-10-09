package Group3.JobsMadeEasy.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomePageController {

    @GetMapping("/")
    public String index() {
        return "Welcome to Jobs Made Easy!!!";
    }

}
