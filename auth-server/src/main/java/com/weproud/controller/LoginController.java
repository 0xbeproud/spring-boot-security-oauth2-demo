package com.weproud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Logan. 81k
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @RequestMapping
    public String login(){
        return "no need auth";
    }
}
