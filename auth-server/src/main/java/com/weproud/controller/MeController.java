package com.weproud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Logan. 81k
 */
@Slf4j
@RestController
@RequestMapping("/me")
public class MeController {
    @RequestMapping
    public Principal getMe(Principal me) {
        log.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        return me;
    }
}
