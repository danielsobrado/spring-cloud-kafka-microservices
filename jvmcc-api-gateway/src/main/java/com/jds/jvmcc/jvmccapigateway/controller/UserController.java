package com.jds.jvmcc.jvmccapigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author J. Daniel Sobrado
 * @version 1.0
 * @since 2022-08-26
 */
@RestController
public class UserController {

    @GetMapping("/user")
    public String index(Principal principal) {
        return principal.getName();
    } 
}
