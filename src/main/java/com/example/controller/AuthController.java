package com.example.controller;

import com.example.dto.Auth;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Suxrob Sattorov, Tue 10:38 AM. 10/11/2022
 */

@Controller
@RequestMapping()
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;

    @RequestMapping( value = "/login", method = GET)
    public String goToLoginPage( Model model ) {
        model.addAttribute("auth", new Auth());
        return "login_page";
    }

    @RequestMapping( value = "/auth", method = POST)
    public String authorization( @ModelAttribute( "auth" ) Auth auth ) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()));

        UserDetails user = (UserDetails) authentication.getPrincipal();
        Optional<SimpleGrantedAuthority> optional = (Optional<SimpleGrantedAuthority>) user.getAuthorities().stream().findAny();
        String role = "";
        if ( optional.isPresent() ) {
            role = optional.get().getAuthority();
        }
        log.info(role);
        if (role.equals("ROLE_USER")) {
            return "redirect:/user";
        } else if (role.equals("ROLE_ADMIN")) {
            return "redirect:/admin";
        }
        return "redirect:/login";

    }
}
