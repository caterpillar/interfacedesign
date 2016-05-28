package org.interfacedesign.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lishaohua on 16-5-27.
 */
@Controller
@RequestMapping("/")
public class HomePageController {

    @RequestMapping
    public String homePage() {
        return "index";
    }
}
