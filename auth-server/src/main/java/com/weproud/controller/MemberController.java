package com.weproud.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Logan. 81k
 */
@RestController
@RequestMapping("/members")
public class MemberController {
    @RequestMapping
    public String hello(){
        return "hello";
    }
}
