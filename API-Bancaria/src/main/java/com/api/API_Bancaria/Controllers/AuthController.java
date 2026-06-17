package com.api.API_Bancaria.Controllers;

import com.api.API_Bancaria.Dtos.LoginRequestDTO;
import com.api.API_Bancaria.services.CustomUserDetailsService;
import com.api.API_Bancaria.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDTO dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getCpf(),
                        dto.getPassword()
                )
        );

        return jwtService.generateJWTToken(dto.getCpf());
    }
}
