package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller illustrating a bug in the Mock MVC test handling of parameters without value
 * @author JB Nizet
 */
@RestController
@RequestMapping("/api/bug")
public class BugController {

    @GetMapping
    public String noParam() {
        return "noParam";
    }

    @GetMapping(params = "foo")
    public String paramFoo() {
        return "paramFoo";
    }
}
