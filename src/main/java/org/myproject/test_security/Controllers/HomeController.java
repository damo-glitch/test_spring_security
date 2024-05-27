package org.myproject.test_security.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/home")
    public String home() {
        return "bienvenue sur la page home du site";
    }

    @GetMapping("/admin")
    public String admin() {
        return "bienvenue sur la page --- admin --- du site";
    }

    @GetMapping("/user")
    public String user() {
        return "bienvenue sur la page --- utilisateur --- du site";
    }
}
