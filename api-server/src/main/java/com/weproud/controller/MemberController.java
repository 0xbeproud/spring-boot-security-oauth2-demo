package com.weproud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author Logan. 81k
 */
@Slf4j
@RestController
@RequestMapping("/members")
public class MemberController {

    @RequestMapping
    public @ResponseBody
    Principal getMembersAll(final Principal principal) {
        log.info("principal: {}", principal);
        log.info("principal.getName(): {}", principal.getName());
        return principal;
    }

//    @RequestMapping
//    public @ResponseBody Authentication getMembersAll(){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//        if (!(authentication instanceof AnonymousAuthenticationToken)) {
//            String currentUserName = authentication.getName();
//            log.info("currentUserName: {}", currentUserName);
//        }
//        log.info("currentPrincipalName: {}", currentPrincipalName);
//        return authentication;
//    }
}
